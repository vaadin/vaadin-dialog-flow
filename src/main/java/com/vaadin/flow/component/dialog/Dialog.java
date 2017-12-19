/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.dialog;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.server.VaadinRequest;

/**
 * Server-side component for the {@code <vaadin-dialog>} element.
 * 
 * @author Vaadin Ltd
 */
@HtmlImport("bower_components/polymer/polymer.html")
@HtmlImport("frontend://flow-component-renderer.html")
public class Dialog extends GeneratedVaadinDialog<Dialog>
        implements HasComponents {

    private Element container;

    /**
     * Creates an empty dialog.
     */
    public Dialog() {
        container = new Element("div", false);
        getElement().appendVirtualChild(container);

        getElement().getNode().runWhenAttached(ui -> {
            String appId = UI.getCurrent().getSession().getService()
                    .getMainDivId(UI.getCurrent().getSession(),
                            VaadinRequest.getCurrent());
            appId = appId.substring(0, appId.indexOf("-"));

            int nodeId = container.getNode().getId();

            String template = "<template><flow-component-renderer appid="
                    + appId + " nodeid=" + nodeId
                    + "></flow-component-renderer></template>";
            getElement().setProperty("innerHTML", template);
        });
    }

    /**
     * Creates a dialog with given components inside.
     * 
     * @param components
     *            the components inside the dialog
     * @see #add(Component...)
     */
    public Dialog(Component... components) {
        this();
        add(components);
    }

    @Override
    public void add(Component... components) {
        assert components != null;
        for (Component component : components) {
            assert component != null;
            container.appendChild(component.getElement());
        }
    }

    @Override
    public void remove(Component... components) {
        for (Component component : components) {
            assert component != null;
            if (container.equals(component.getElement().getParent())) {
                container.removeChild(component.getElement());
            } else {
                throw new IllegalArgumentException("The given component ("
                        + component + ") is not a child of this component");
            }
        }
    }

    @Override
    public void removeAll() {
        container.removeAllChildren();
    }

    /**
     * Opens the dialog.
     */
    public void open() {
        setOpened(true);
    }

    /**
     * Closes the dialog.
     */
    public void close() {
        setOpened(false);
    }

    @Override
    public void setOpened(boolean opened) {
        if (opened && !getElement().getNode().isAttached()
                && UI.getCurrent() != null) {
            // Add the element to body when it's opened,
            // if it hasn't been attached already somewhere.
            UI.getCurrent().add(this);
        }
        super.setOpened(opened);
    }

}
