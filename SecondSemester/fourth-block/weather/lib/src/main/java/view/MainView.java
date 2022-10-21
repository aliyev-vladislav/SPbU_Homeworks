package view;

import controller.StateController;

import javax.swing.*;
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
        this.selectorWeather = selectorWeather;

        JButton refreshButton = new JButton("Update");
        add(refreshButton, BorderLayout.SOUTH);
        refreshButton.addActionListener(e -> {
            updateDataFromService(selectorWeather.getSelectedWeather());
            outputDataFromService(selectorWeather.getSelectedWeather());
            revalidate();
        });
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
