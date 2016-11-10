/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.view;

import com.sjm.financialapplication.main.AppContext;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sm6668
 */
@Component
public class ViewImpl implements View {

    @Autowired
    private AppContext appContext;

    @Autowired
    private ViewMap viewMap;

    @Override
    public boolean loadView(Views view) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ViewImpl.class.getClassLoader().
                    getResource(view.getLocation()));

            //Have spring set the controller factory
            loader.setControllerFactory((Class<?> c)
                    -> appContext.getAppContext().getBean(c));

            viewMap.put(view, loader.load());
            return true;

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean removeView(Views view) {
        if (viewMap.containsKey(view)) {
            viewMap.remove(view);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Object getView(Views view) {
        if (viewMap.containsKey(view)) {
            return viewMap.get(view);
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return viewMap.size();
    }
}
