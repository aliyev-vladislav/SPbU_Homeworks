package view;

import javax.swing.*;

public class SelectorWeather extends JMenuBar {

    private JMenu sourceMenu;
    private ButtonGroup group;

    public SelectorWeather() {
        this("Services");
    }
    public SelectorWeather(String name) {
        sourceMenu = new JMenu(name);
        this.add(sourceMenu);

        group = new ButtonGroup();
    }

    public void addService(String service) {
        var newItem = new JRadioButtonMenuItem(service);
        sourceMenu.add(newItem);
        if (sourceMenu.getItemCount() == 1) {
            sourceMenu.getItem(0).setSelected(true);
        }

        group.add(newItem);
    }

    public String getSelectedWeather() {
        for (int i = 0; i < sourceMenu.getItemCount(); i++) {
            if (sourceMenu.getItem(i).isSelected()) {
                return sourceMenu.getItem(i).getText();
            }
        }

        return null;
    }
}
