package view;

import controller.StateController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainView extends JPanel {
    private Map<String, StateController> controller;
    private Map<String, WeatherView> weatherView;
    private SelectorWeather selectorWeather;
    public MainView(SelectorWeather selectorWeather) {
        setLayout(new BorderLayout());
        this.controller = new HashMap<>();
        this.weatherView = new HashMap<>();

        setBackground(new Color(43, 43, 43));

        // MenuBar
        this.selectorWeather = selectorWeather;

        // Refresh button
        JButton refreshButton = new JButton("Update");
        add(refreshButton, BorderLayout.SOUTH);
        refreshButton.addActionListener(e -> {
            updateDataFromService(selectorWeather.getSelectedWeather());
            outputDataFromService(selectorWeather.getSelectedWeather());
            revalidate();
        });
        refreshButton.setForeground(Color.BLACK);
        refreshButton.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);

        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        refreshButton.setBorder(compound);
        refreshButton.setFocusPainted(false);
        refreshButton.setFont(new Font("Roboto", Font.BOLD, 15));
    }
    public void addService(String name, WeatherView view, StateController controller) {
        this.weatherView.put(name, view);
        this.controller.put(name, controller);
        this.selectorWeather.addService(name);

        if (weatherView.size() == 1) {
            updateDataFromService(name);
            outputDataFromService(name);
        }
    }

    public void updateDataFromService(String service) {
        this.controller.get(service).updateState();
    }

    public void outputDataFromService(String service) {
        weatherView.get(service).outputData();
        add(weatherView.get(service), BorderLayout.CENTER);
    }
}
