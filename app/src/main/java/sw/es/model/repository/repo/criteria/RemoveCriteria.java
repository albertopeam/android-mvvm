package sw.es.model.repository.repo.criteria;


import sw.es.model.repository.repo.exception.NoMoreCriteriaException;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class RemoveCriteria {


    private static final int COMMIT = 0;
    private static final int PUSH = COMMIT + 1;
    private int type;


    public static RemoveCriteria newCommit(){
        return new RemoveCriteria(COMMIT);
    }


    public static RemoveCriteria newPush(){
        return new RemoveCriteria(COMMIT);
    }


    private RemoveCriteria(int type) {
        this.type = type;
    }


    public static RemoveCriteria next(RemoveCriteria storeCriteria) throws NoMoreCriteriaException {
        if (storeCriteria.type == COMMIT){
            return newPush();
        }else{
            throw new NoMoreCriteriaException();
        }
    }

    public boolean isCommit(){
        return type == COMMIT;
    }

    public boolean isPush(){
        return type == PUSH;
    }
}
