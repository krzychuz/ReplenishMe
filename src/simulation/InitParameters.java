package simulation;



public class InitParameters {

    private ParametersReader parametersReader;

    public InitParameters() {
        parametersReader = new ParametersReader("import_data/parameters.txt");
        parametersReader.getDate("start_date");
    }
}
