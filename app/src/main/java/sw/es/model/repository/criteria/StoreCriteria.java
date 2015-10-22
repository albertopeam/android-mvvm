package sw.es.model.repository.criteria;


import sw.es.model.repository.exception.NoMoreCriteriaException;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class StoreCriteria {


    private static final int COMMIT = 0;
    private static final int PUSH = COMMIT + 1;
    private int type;


    public static StoreCriteria newCommit(){
        return new StoreCriteria(COMMIT);
    }


    public static StoreCriteria newPush(){
        return new StoreCriteria(COMMIT);
    }


    private StoreCriteria(int type) {
        this.type = type;
    }


    public static StoreCriteria next(StoreCriteria storeCriteria) throws NoMoreCriteriaException {
        if (storeCriteria.type == COMMIT){
            return newPush();
        }else{
            throw new NoMoreCriteriaException();
        }
    }
}
