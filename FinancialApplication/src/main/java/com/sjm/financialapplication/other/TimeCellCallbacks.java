/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.other;

import java.time.LocalDate;
import java.time.Month;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author sm6668
 */
public class TimeCellCallbacks {

    public static class DayCellCallbacks {

        public static class Past<S, T> implements Callback<DatePicker, DateCell> {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        }

        public static class Future<S, T> implements Callback<DatePicker, DateCell> {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        }

    }

    public static class MonthCellCallback implements Callback<ListView<Month>, ListCell<Month>> {

        @Override
        public ListCell<Month> call(ListView<Month> param) {
            return new ListCell<Month>() {

                @Override
                public void updateItem(Month item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.compareTo(LocalDate.now().getMonth()) > 0) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }

            };
        }

    }
}
