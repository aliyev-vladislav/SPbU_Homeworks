package view;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import controller.StateController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends JFrame {
    public App() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException exc) {
            //
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        var menuBar = new SelectorWeather();
        MainView mainView = new MainView(menuBar);
        setContentPane(mainView);
        setSize(500, 400);
        setResizable(false);
        setJMenuBar(menuBar);

        StateController openWeatherController = (StateController) context.getBean("openWeatherStateController");
        WeatherView openWeatherView = (WeatherView) context.getBean("openWeatherView");

        StateController tomorrowController = (StateController) context.getBean("tomorrowStateController");
        WeatherView tomorrowView = (WeatherView) context.getBean("tomorrowView");

        mainView.addService("OpenWeather", openWeatherView, openWeatherController);
        mainView.addService("Tomorrow", tomorrowView, tomorrowController);

        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new App();
    }
}
