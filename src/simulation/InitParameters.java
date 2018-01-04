package simulation;


public class InitParameters {

    private ParametersReader parametersReader;

    public InitParameters() {
        parametersReader = new ParametersReader("import_data/parameters.txt");
        GlobalParameters.CurrentTime = parametersReader.getDate("start_date");
        GlobalParameters.BwForecastConsumption = parametersReader.getInt("bw_fcst_consumption");
        GlobalParameters.FwForecastConsumption = parametersReader.getInt("fw_fcst_consumption");
        GlobalParameters.Tick = parametersReader.getInt("tick");;
        GlobalParameters.FirmedZone = parametersReader.getInt("firmed_zone");
        GlobalParameters.QualityCheck = parametersReader.getInt("quality_check");
        GlobalParameters.OrderLeadTime = parametersReader.getInt("order_leadtime");
        GlobalParameters.SimulationEndDate = parametersReader.getDate("end_date");
    }
}
