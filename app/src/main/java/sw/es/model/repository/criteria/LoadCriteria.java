package sw.es.model.repository.criteria;

import sw.es.model.repository.exception.NoMoreCriteriaException;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class LoadCriteria {


    private static final int FETCH = 0;
    private static final int PULL = FETCH + 1;
    private int type;


    public static LoadCriteria newFetch(){
        return new LoadCriteria(FETCH);
    }


    public static LoadCriteria newPull(){
        return new LoadCriteria(PULL);
    }


    public LoadCriteria(int type) {
        this.type = type;
    }


    public boolean isNewData(){
        return type == PULL;
    }


    public LoadCriteria next() throws NoMoreCriteriaException {
        if (type == FETCH){
            return newPull();
        }else{
            throw new NoMoreCriteriaException();
        }
    }
}
