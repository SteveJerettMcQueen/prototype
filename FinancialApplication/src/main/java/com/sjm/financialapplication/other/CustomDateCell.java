/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.other;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 *
 * @author Steve
 * @param <S>
 * @param <T>
 */
public class CustomDateCell<S, T> extends TableCell<Object, Date> {

    private DatePicker datePicker;
    private Callback<DatePicker, DateCell> dayCellFactory;
    private final SimpleDateFormat smp;

    public CustomDateCell() {
        smp = new SimpleDateFormat("MM/dd/yyyy");
    }

    public CustomDateCell(Callback<DatePicker, DateCell> dayCellFactory) {
        smp = new SimpleDateFormat("MM/dd/yyyy");
        this.dayCellFactory = dayCellFactory;
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(() -> {
            datePicker.requestFocus();
            datePicker.getEditor().selectAll();
        });
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(smp.format(getItem()));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void commitEdit(Date newValue) {
        super.commitEdit(newValue);
    }

    @Override
    public void updateItem(Date item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (isEditing()) {
            if (datePicker != null) {
                datePicker.setValue(getLocalDate());
            }
            setGraphic(datePicker);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else {
            setText(getLocalDate().toString());
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }

    private void createDatePicker() {
        datePicker = new DatePicker(getLocalDate());
        datePicker.setShowWeekNumbers(true);
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        datePicker.setOnKeyPressed((KeyEvent t) -> {
            if (null != t.getCode()) {
                switch (t.getCode()) {
                    case ENTER:
                        commitEdit(convertToDate());
                        break;
                    case ESCAPE:
                        cancelEdit();
                        break;
                    case TAB:
                        commitEdit(convertToDate());
                        TableColumn nextColumn = getNextColumn(!t.isShiftDown());
                        if (nextColumn != null) {
                            getTableView().edit(getTableRow().getIndex(), nextColumn);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        datePicker.focusedProperty().addListener(
                (observ, oldValue, newValue) -> {
                    if (!newValue && datePicker != null) {
                        commitEdit(convertToDate());
                    }
                });
    }

    private LocalDate getLocalDate() {
        int day = 0;
        int month = 0;
        int year = 0;

        //Converts the date attribute to localDate to set into the datepicker
        String dateAsStr = smp.format(getItem());

        try {
            year = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
            month = Integer.parseInt(dateAsStr.substring(0, 2));
            day = Integer.parseInt(dateAsStr.substring(3, 5));
        } catch (NumberFormatException e) {
            System.out.println("Date / unexpected error " + e);
        }

        return LocalDate.of(year, month, day);//Has to be of this format
    }

    private Date convertToDate() {
        //Convert local date to date to set for commit edit
        if (datePicker.getValue() != null) {
            return Date.from(datePicker.getValue().atStartOfDay().atZone(
                    ZoneId.systemDefault()).toInstant());
        } else {
            return null;
        }

    }

    /**
     *
     * @param forward true gets the column to the right, false the column to the
     * left of the current column
     * @return
     */
    private TableColumn<Object, ?> getNextColumn(boolean forward) {
        List<TableColumn<Object, ?>> columns = new ArrayList<>();
        for (TableColumn<Object, ?> column : getTableView().getColumns()) {
            columns.addAll(getLeaves(column));
        }
        //There is no other column that supports editing.
        if (columns.size() < 2) {
            return null;
        }
        int currentIndex = columns.indexOf(getTableColumn());
        int nextIndex = currentIndex;
        if (forward) {
            nextIndex++;
            if (nextIndex > columns.size() - 1) {
                nextIndex = 0;
            }
        } else {
            nextIndex--;
            if (nextIndex < 0) {
                nextIndex = columns.size() - 1;
            }
        }
        return columns.get(nextIndex);
    }

    private List<TableColumn<Object, ?>> getLeaves(TableColumn<Object, ?> root) {
        List<TableColumn<Object, ?>> columns = new ArrayList<>();
        if (root.getColumns().isEmpty()) {
            //We only want the leaves that are editable.
            if (root.isEditable()) {
                columns.add(root);
            }
            return columns;
        } else {
            for (TableColumn<Object, ?> column : root.getColumns()) {
                columns.addAll(getLeaves(column));
            }
            return columns;
        }
    }
}
