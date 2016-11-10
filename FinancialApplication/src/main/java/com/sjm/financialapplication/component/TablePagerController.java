/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.concurrency.AppExecutorService;
import com.sjm.financialapplication.concurrency.TablePagerService;
import com.sjm.financialapplication.presenter.DataPresenter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author sm6668
 */
@Component
public class TablePagerController implements Initializable {

    private int pageNumber;

    private final SimpleIntegerProperty pageNumberProperty
            = new SimpleIntegerProperty();

    private final SimpleIntegerProperty itemsPerPageProperty
            = new SimpleIntegerProperty();

    private final SimpleIntegerProperty numOfItemsProperty
            = new SimpleIntegerProperty();

    private final SimpleIntegerProperty pageTotalProperty
            = new SimpleIntegerProperty();

    private final SimpleBooleanProperty rangeProperty
            = new SimpleBooleanProperty();

    private final IntegerBinding pageBinding = new IntegerBinding() {
        {
            bind(numOfItemsProperty, itemsPerPageProperty);
        }

        ;
        
        @Override
        protected int computeValue() {
            int numOfItems = numOfItemsProperty().get();
            int itemsPerPage = itemsPerPageProperty().get();
            int numOfPages = numOfItems / itemsPerPage;

            if (numOfItems % itemsPerPage != 0) {
                return numOfPages + 1;
            } else {
                return (numOfPages == 0) ? 1 : numOfPages;
            }

        }

    };

    @Autowired
    private AppExecutorService aes;

    @Autowired
    private DataPresenter dp;

    @Autowired
    private TablePagerService tablePagerService;

    @FXML
    private HBox pageControlBox;

    @FXML
    private HBox pageNavBox;

    @FXML
    private Button prevBtn, nextBtn;

    @FXML
    private TextField pageTF;

    @FXML
    private Label pageTotalLabel;

    @FXML
    private ProgressIndicator pageIndicator;

    @FXML
    private ComboBox<Integer> itemsPerPageCB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pageNumberProperty.set(pageNumber = 1);

        itemsPerPageCB.setValue(25);
        itemsPerPageCB.setItems(FXCollections.observableArrayList(25, 50, 75));
        itemsPerPageProperty.set(itemsPerPageCB.getValue());

        tablePagerService.setExecutor(aes.getExecutorServicePool());
        tablePagerService.setOnSucceeded(e -> tablePagerServiceSucceeded());

        //Initialization Field Bindings
        numOfItemsProperty.bind(dp.totalAccountTransactionsProperty());
        pageTotalProperty.bind(pageBinding);
        rangeProperty.bind(pageNumberProperty.greaterThanOrEqualTo(1).and(pageNumberProperty.lessThanOrEqualTo(pageTotalProperty)));
        tablePagerService.startProperty().bind(pageNumberProperty.subtract(1).multiply(itemsPerPageProperty));
        tablePagerService.maxProperty().bind(itemsPerPageProperty);

        //Initialization UI Bindings
        pageNavBox.disableProperty().bind(tablePagerService.runningProperty());
        prevBtn.disableProperty().bind(rangeProperty.not().or(pageNumberProperty.isEqualTo(1)));
        nextBtn.disableProperty().bind(rangeProperty.not().or(pageNumberProperty.isEqualTo(pageTotalProperty)));
        pageTotalLabel.textProperty().bindBidirectional(pageTotalProperty, new NumberStringConverter());
        pageIndicator.visibleProperty().bind(tablePagerService.runningProperty());
        pageIndicator.progressProperty().bind(tablePagerService.progressProperty());
        itemsPerPageCB.disableProperty().bind(tablePagerService.runningProperty());
    }

    protected final TablePagerService getTablePagerService() {
        return tablePagerService;
    }

    protected HBox getPageControlBox() {
        return pageControlBox;
    }

    protected HBox getPageNavBox() {
        return pageNavBox;
    }

    protected SimpleIntegerProperty pageNumberProperty() {
        return pageNumberProperty;
    }

    protected void setPageNumber(int value) {
        pageNumberProperty.set(pageNumber = value);
    }

    protected SimpleIntegerProperty itemsPerPageProperty() {
        return itemsPerPageProperty;
    }

    protected void setItemsPerPageProperty(int value) {
        itemsPerPageProperty.set(value);
    }

    protected SimpleIntegerProperty numOfItemsProperty() {
        return numOfItemsProperty;
    }

    protected void setNumOfItems(int value) {
        numOfItemsProperty.set(value);
    }

    protected int getPageTotal() {
        return pageTotalProperty.get();
    }

    private String getPage(int i) {
        return Integer.toString(i);
    }

    private void beginPageService() {
        tablePagerService.start();
    }

    private void tablePagerServiceSucceeded() {
        pageTF.setText(getPage(pageNumberProperty.get()));
        tablePagerService.reset();
    }

    private boolean isValidPageNumber(int pageNumber) {
        return pageNumber >= 1 && pageNumber <= getPageTotal();
    }

    private int parse(String pageNumber) throws NumberFormatException {
        return Integer.parseInt(pageNumber);
    }

    @FXML
    private void setPrevious(ActionEvent event) {
        int prevPage = --pageNumber;
        pageNumberProperty.set(prevPage);
        beginPageService();
        event.consume();
    }

    @FXML
    private void setNext(ActionEvent event) {
        int nextPage = ++pageNumber;
        pageNumberProperty.set(nextPage);
        beginPageService();
        event.consume();
    }

    @FXML
    private void goToPage(ActionEvent event) {
        try {
            int myPage = parse(pageTF.getText());
            if (isValidPageNumber(myPage)) {
                pageNumber = myPage;
                pageNumberProperty.set(pageNumber);
                beginPageService();
            } else {
                pageTF.setText(getPage(pageNumber));
            }
        } catch (NumberFormatException ex) {
            pageTF.setText(getPage(pageNumber));
        }
        event.consume();
    }

    @FXML
    private void resetItemsPerPage(ActionEvent event) {
        pageNumberProperty.set(pageNumber = 1);
        itemsPerPageProperty.set(itemsPerPageCB.getValue());
        beginPageService();
        event.consume();
    }

}
