package sw.es.model.repository.repo.criteria;

import sw.es.model.repository.repo.exception.NoMoreCriteriaException;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class LoadCriteria {


    private static final int GET = 0;
    private static final int REFRESH = GET + 1;
    private int type;


    public static LoadCriteria newGet(){
        return new LoadCriteria(GET);
    }


    public static LoadCriteria newRefresh(){
        return new LoadCriteria(REFRESH);
    }


    public LoadCriteria(int type) {
        this.type = type;
    }


    public boolean isNewData(){
        return type == REFRESH;
    }


    public LoadCriteria next() throws NoMoreCriteriaException {
        if (type == GET){
            return newRefresh();
        }else{
            throw new NoMoreCriteriaException();
        }
    }


    public boolean isGet(){
        return type == GET;
    }


    public boolean isRefresh(){
        return type == REFRESH;
    }
}
