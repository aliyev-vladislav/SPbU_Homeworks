package view;

import weather_model.WeatherModel;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class WeatherView extends JPanel {
    private WeatherModel weatherModel;
    private JScrollPane scrollPane;

    public WeatherView(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
        this.scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }

    private String dataOrMessage(String item) {
        if (item == null) {
            return "data unavailable";
        }
        return item;
    }

    public void outputData() {
        var data = weatherModel.getData();
        Object[][] infoRow = {
                {"Air Temperature, \u00B0C", dataOrMessage(data.getAirTemperature())},
                {"Real feel, \u00B0C", dataOrMessage(data.getRealFeelTemp())},
                {"Cloud cover, %", dataOrMessage(data.getCloudCover())},
                {"Pressure, gPa", dataOrMessage(data.getPressure())},
                {"Humidity, %", dataOrMessage(data.getHumidity())},
                {"Wind speed, mm/s", dataOrMessage(data.getWindSpeed())},
        };
        JTable dataTable = new JTable(infoRow, new Object[]{"Metric", "Value"}) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                Dimension dim = super.getPreferredScrollableViewportSize();
                dim.height = getPreferredSize().height;
                return dim;
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        dataTable.setRowHeight(30);
        dataTable.setPreferredScrollableViewportSize(dataTable.getPreferredScrollableViewportSize());
        dataTable.setFont(new Font("SansSerif", Font.PLAIN, 20));
        scrollPane.setViewportView(dataTable);
    }
}
