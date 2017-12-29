package simulation;


public class InitParameters {

    private ParametersReader parametersReader;

    public InitParameters() {
        parametersReader = new ParametersReader("import_data/parameters.txt");
        GlobalParameters.currentTime = parametersReader.getDate("start_date");
        GlobalParameters.BwForecastConsumption = parametersReader.getInt("bw_fcst_consumption");
        GlobalParameters.FwForecastConsumption = parametersReader.getInt("fw_fcst_consumption");
        GlobalParameters.Tick = parametersReader.getInt("tick");;
        GlobalParameters.LoggingLevel = parametersReader.getInt("logging_level");
    }
}
