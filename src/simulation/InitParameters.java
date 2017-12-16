package simulation;



public class InitParameters {

    private ParametersReader parametersReader;

    public InitParameters() {
        parametersReader = new ParametersReader("import_data/parameters.txt");
        System.out.println(parametersReader.getString("start_date"));
    }
}
