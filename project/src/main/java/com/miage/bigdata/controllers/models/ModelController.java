package com.miage.bigdata.controllers.models;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.models.Item;

public class ModelController {
    private ModelDbController[] controllers;

    public ModelController() {
        controllers = new ModelDbController[]{
                new ColumnController(),
                new DocumentController(),
                new GraphController(),
                new KeyValueController(),
                new RelationalController()
        };
    }

    public ItemController getItemController(Class<? extends Item> cls) {
        for (ModelDbController controller : controllers) {
            ItemController itemController = controller.getItemController(cls);

            if (itemController != null) {
                return itemController;
            }
        }

        return null;
    }
}
