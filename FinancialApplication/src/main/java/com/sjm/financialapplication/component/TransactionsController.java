/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.concurrency.AccountTransactionWorkers.AccountTransactionDeleter;
import com.sjm.financialapplication.concurrency.AccountTransactionWorkers.AccountTransactionSaver;
import com.sjm.financialapplication.concurrency.AccountTransactionWorkers.AccountTransactionUpdater;
import com.sjm.financialapplication.presenter.DataPresenter;
import com.sjm.financialapplication.model.Account;
import com.sjm.financialapplication.model.AccountTransaction;
import com.sjm.financialapplication.presenter.AccountTransactionFX;
import com.sjm.financialapplication.other.CustomDateCell;
import com.sjm.financialapplication.presenter.Category;
import static com.sjm.financialapplication.presenter.Category.INCOME;
import static com.sjm.financialapplication.presenter.Category.UNCATEGORIZED;
import com.sjm.financialapplication.presenter.TransactionType;
import static com.sjm.financialapplication.presenter.TransactionType.DEPOSIT;
import static com.sjm.financialapplication.presenter.TransactionType.WITHDRAWAL;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sjm.financialapplication.concurrency.AppExecutorService;
import com.sjm.financialapplication.concurrency.ViewWorkers.AccountViewer;
import com.sjm.financialapplication.other.TimeCellCallbacks.DayCellCallbacks.Past;
import java.util.concurrent.ExecutorService;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import static com.sjm.financialapplication.util.LogicUtils.isValidCombo;

/**
 *
 * @author sm6668
 */
@Component
public class TransactionsController implements Initializable {

    @Autowired
    private AppExecutorService aes;

    @Autowired
    private AccountViewer av;

    @Autowired
    private AccountTransactionDeleter atd;

    @Autowired
    private AccountTransactionSaver ats;

    @Autowired
    private AccountTransactionUpdater atu;

    @Autowired
    private DataPresenter dataPresenter;

    @Autowired
    private GoalsController gc;

    @Autowired
    private TablePagerController tpc;

    @Autowired
    private MessagePaneController mpc;

    @Autowired
    private ProgressPaneController ppc;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField searchTF, descTF, creditTF, debitTF;

    @FXML
    private TableView<AccountTransactionFX> transTableView;

    @FXML
    private TableColumn<AccountTransactionFX, Integer> transIdCol;

    @FXML
    private TableColumn<AccountTransactionFX, Date> dateCol;

    @FXML
    private TableColumn<AccountTransactionFX, Category> categoryCol;

    @FXML
    private TableColumn<AccountTransactionFX, String> descCol, creditCol, debitCol, balCol;

    @FXML
    private TableColumn<AccountTransactionFX, TransactionType> typeCol;

    @FXML
    private Button addGoalBtn;

    @FXML
    private HBox actionBox;

    @FXML
    private BorderPane tablePane, goalPane;

    @FXML
    private StackPane tableStackPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setExecutors(aes.getExecutorServicePool());
        atd.setOnSucceeded(e -> atdSucceeded());
        ats.setOnSucceeded(e -> atsSucceeded());
        atu.setOnSucceeded(e -> atuSucceeded());

        av.startProperty().bind(tpc.getTablePagerService().startProperty());
        av.maxProperty().bind(tpc.getTablePagerService().maxProperty());

        tablePane.widthProperty().addListener(widthListener());
        transTableView.itemsProperty().bind(dataPresenter.accountTransactionsFXProperty());
        setTableViewColumnBindings(transTableView);

        transIdCol.setCellValueFactory(p -> p.getValue().transIdProperty());

        //Editable Date Column
        dateCol.setCellValueFactory(p -> p.getValue().transDateProperty());
        dateCol.setCellFactory((TableColumn<AccountTransactionFX, Date> p)
                -> new CustomDateCell(new Past()));

        //Editable Category Column
        categoryCol.setCellValueFactory(p -> p.getValue().categoryProperty());
        categoryCol.setCellFactory(ComboBoxTableCell.forTableColumn(dataPresenter.getCategories()));

        //Editable Description Column
        descCol.setCellValueFactory(p -> p.getValue().descriptionProperty());
        descCol.setCellFactory(TextFieldTableCell.<AccountTransactionFX>forTableColumn());

        //Editable Type Column
        typeCol.setCellValueFactory(p -> p.getValue().typeProperty());
        typeCol.setCellFactory(ComboBoxTableCell.forTableColumn(dataPresenter.getTransTypes()));

        creditCol.setCellValueFactory(p -> p.getValue().creditProperty());
        debitCol.setCellValueFactory(p -> p.getValue().debitProperty());
        balCol.setCellValueFactory(p -> p.getValue().balanceProperty());

        datePicker.setValue(LocalDate.now());
        datePicker.setDayCellFactory(new Past());

        addGoalBtn.addEventHandler(ActionEvent.ACTION, hideHandler);

        actionBox.disableProperty().bind(ats.runningProperty());

        mpc.getMessagePane().visibleProperty().bind(ats.runningProperty());
        mpc.getMessageLabel().textProperty().bind(ats.messageProperty());
        tpc.getPageControlBox().disableProperty().bind(ats.runningProperty());

        //Temporary
        goalPane.setBackground(new Background(
                new BackgroundFill(Paint.valueOf("rgba(255, 255, 255, 0.85)"),
                        CornerRadii.EMPTY, Insets.EMPTY)));

    }

    @FXML
    private void search(ActionEvent event) {
        //Apply Search
        event.consume();
    }

    @FXML
    private void closeGoalPane(MouseEvent event) {
        if (!gc.getExpDatePicker().isFocused()) {
            addGoalBtn.fire();
        }
        event.consume();
    }

    @FXML
    private void addTransaction(ActionEvent event) {
        if (isValidCombo(
                !descTF.getText().isEmpty(),
                !creditTF.getText().isEmpty(),
                !debitTF.getText().isEmpty()
        )) {

            LocalDate localDate = datePicker.getValue();
            Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            String desc = descTF.getText();
            boolean isCredit = !creditTF.getText().isEmpty();
            TransactionType type = (isCredit) ? DEPOSIT : WITHDRAWAL;
            Category category = (isCredit) ? INCOME : UNCATEGORIZED;
            BigDecimal amount = new BigDecimal((isCredit) ? creditTF.getText() : "-" + debitTF.getText());

            Account a = dataPresenter.accountFXProperty().get().getAccount();
            AccountTransaction at = new AccountTransaction();

            at.setAccount(a);//For Id because detached entity
            at.setTransDate(date);
            at.setCategory(category.name());
            at.setDescription(desc);
            at.setTransType(type.name());
            at.setAmount(amount);
            at.setCurBalance(a.getAccountCurBal().add(amount));

            save(at);

        } else {

            //Incorrect combo
        }

        event.consume();

    }

    @FXML
    private void undoTransaction(ActionEvent event) {
        if (!transTableView.getItems().isEmpty()) {
            AccountTransactionFX atfx = transTableView.getItems().get(0);
            AccountTransaction at = atfx.getAccountTransaction();
            delete(at);
        } else {

            //No Transactions
        }
        event.consume();
    }

    @FXML
    private void updateTransDate(TableColumn.CellEditEvent<AccountTransactionFX, Date> event) {
        int index = event.getTablePosition().getRow();
        AccountTransactionFX atfx = event.getTableView().getItems().get(index);
        AccountTransaction at = atfx.getAccountTransaction();
        at.setTransDate(event.getNewValue());
        update(at);
        event.consume();
    }

    @FXML
    private void updateCategory(TableColumn.CellEditEvent<AccountTransactionFX, Category> event) {
        int index = event.getTablePosition().getRow();
        AccountTransactionFX atfx = event.getTableView().getItems().get(index);
        AccountTransaction at = atfx.getAccountTransaction();
        at.setCategory(event.getNewValue().name());
        update(at);
        event.consume();
    }

    @FXML
    private void updateDescription(TableColumn.CellEditEvent<AccountTransactionFX, String> event) {
        int index = event.getTablePosition().getRow();
        AccountTransactionFX atfx = event.getTableView().getItems().get(index);
        AccountTransaction at = atfx.getAccountTransaction();
        at.setDescription(event.getNewValue());
        update(at);
        event.consume();
    }

    @FXML
    private void updateType(TableColumn.CellEditEvent<AccountTransactionFX, TransactionType> event) {
        int index = event.getTablePosition().getRow();
        AccountTransactionFX atfx = event.getTableView().getItems().get(index);
        AccountTransaction at = atfx.getAccountTransaction();
        at.setTransType(event.getNewValue().name());
        update(at);
        event.consume();
    }

    private void setExecutors(ExecutorService es) {
        av.setExecutor(es);
        atd.setExecutor(es);
        ats.setExecutor(es);
        atu.setExecutor(es);
    }

    private void atdSucceeded() {
        atd.reset();
        transTableView.getItems().remove(0);//Remove from tableview
        resetAccountView();
    }

    private void atsSucceeded() {
        descTF.clear();
        creditTF.clear();
        debitTF.clear();
        ats.reset();
        resetAccountView();
    }

    private void atuSucceeded() {
        atu.reset();
        resetAccountView();
    }

    private void delete(AccountTransaction at) {
        resetUIComponents(atd);
        atd.setAccountTransaction(at);
        atd.start();
    }

    private void save(AccountTransaction at) {
        resetUIComponents(ats);
        ats.setAccountTransaction(at);
        ats.start();
    }

    private void update(AccountTransaction at) {
        resetUIComponents(atu);
        atu.setAccountTransaction(at);
        atu.start();
    }

    private void resetAccountView() {
        av.setOnSucceeded(t -> av.reset());
        av.start();
    }

    private void setTableViewColumnBindings(TableView tv) {
        SimpleIntegerProperty smallest = new SimpleIntegerProperty();
        SimpleIntegerProperty middle = new SimpleIntegerProperty();
        SimpleIntegerProperty largest = new SimpleIntegerProperty();

        smallest.bind(tv.maxWidthProperty().multiply(.07625));
        middle.bind(tv.maxWidthProperty().multiply(.12));
        largest.bind(tv.maxWidthProperty().multiply(.19375));

        transIdCol.maxWidthProperty().bind(smallest);
        transIdCol.prefWidthProperty().bind(transIdCol.maxWidthProperty());

        dateCol.maxWidthProperty().bind(middle);
        dateCol.prefWidthProperty().bind(dateCol.maxWidthProperty());

        categoryCol.maxWidthProperty().bind(middle);
        categoryCol.prefWidthProperty().bind(categoryCol.maxWidthProperty());

        descCol.maxWidthProperty().bind(largest);
        descCol.prefWidthProperty().bind(descCol.maxWidthProperty());

        typeCol.maxWidthProperty().bind(middle);
        typeCol.prefWidthProperty().bind(typeCol.maxWidthProperty());

        creditCol.maxWidthProperty().bind(middle);
        creditCol.prefWidthProperty().bind(creditCol.maxWidthProperty());

        debitCol.maxWidthProperty().bind(middle);
        debitCol.prefWidthProperty().bind(debitCol.maxWidthProperty());

        balCol.maxWidthProperty().bind(middle);
        balCol.prefWidthProperty().bind(balCol.maxWidthProperty());
    }

    private ChangeListener<Number> widthListener() {
        return (observ, oldVal, newVal) -> {
            transTableView.setMaxWidth(newVal.doubleValue());
        };
    }

    private void resetUIComponents(Service s) {
        unbindUIComponents();
        bindUIComponents(s);
    }

    private void unbindUIComponents() {
        actionBox.disableProperty().unbind();
        tpc.getPageControlBox().disableProperty().unbind();
        mpc.getMessagePane().visibleProperty().unbind();
        mpc.getMessageLabel().textProperty().unbind();
//        ppc.getProgressPane().visibleProperty().unbind();
//        ppc.getProgressIndicator().progressProperty().unbind();

    }

    private void bindUIComponents(Service s) {
        actionBox.disableProperty().bind(s.runningProperty());
        tpc.getPageControlBox().disableProperty().bind(s.runningProperty());
        mpc.getMessagePane().visibleProperty().bind(s.runningProperty());
        mpc.getMessageLabel().textProperty().bind(s.messageProperty());
//        ppc.getProgressPane().visibleProperty().bind(s.runningProperty());
//        ppc.getProgressIndicator().progressProperty().bind(s.progressProperty());

        if (!tableStackPane.getChildren().contains(mpc.getMessagePane())) {
            tableStackPane.getChildren().add(mpc.getMessagePane());
        }
    }

    /*
    ** Animations
     */
    private final EventHandler<ActionEvent> showHandler = (ActionEvent t) -> {
        KeyValue kvFade = new KeyValue(goalPane.opacityProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(75), kvFade);
        Timeline hide = new Timeline(kf);
        hide.setOnFinished(e -> setShow());
        hide.play();
    };

    private final EventHandler<ActionEvent> hideHandler = (ActionEvent t) -> {
        goalPane.setVisible(true);
        KeyValue kvFade = new KeyValue(goalPane.opacityProperty(), 1);
        KeyFrame kf = new KeyFrame(Duration.millis(75), kvFade);
        Timeline show = new Timeline(kf);
        show.setOnFinished(e -> {
            setHide();
            gc.getExpDatePicker().getEditor().clear();
            gc.getTargetTF().clear();
            gc.getMinTF().clear();
        });
        show.play();
    };

    private void setShow() {
        goalPane.setVisible(false);
        addGoalBtn.removeEventHandler(ActionEvent.ACTION, showHandler);
        addGoalBtn.addEventHandler(ActionEvent.ACTION, hideHandler);
    }

    private void setHide() {
        goalPane.setVisible(true);
        addGoalBtn.removeEventHandler(ActionEvent.ACTION, hideHandler);
        addGoalBtn.addEventHandler(ActionEvent.ACTION, showHandler);
    }
}
