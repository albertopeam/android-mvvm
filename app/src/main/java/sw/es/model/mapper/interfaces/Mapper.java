package sw.es.model.mapper.interfaces;

public interface Mapper<Input, Output> {
    Output map(Input input);
    Input remap(Output output);
}