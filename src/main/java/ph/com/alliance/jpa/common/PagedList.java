package ph.com.alliance.jpa.common;

import java.util.List;

/**
 * The ApiResult class wraps the response data for the API Request made to the
 * server.
 */
public class PagedList {
    private long pageCount;
    private int pageNumber;
    private int maxRecords;
    private List<?> resultList;

    /**
     * @return the pageCount
     */
    public long getPageCount() {
        return pageCount;
    }

    /**
     * @param l the pageCount to set
     */
    public void setPageCount(long l) {
        this.pageCount = l;
    }

    /**
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @return the maxRecords
     */
    public int getMaxRecords() {
        return maxRecords;
    }

    /**
     * @param maxRecords the maxRecords to set
     */
    public void setMaxRecords(int maxRecords) {
        this.maxRecords = maxRecords;
    }

    /**
     * @return the resultList
     */
    public List<?> getResultList() {
        return resultList;
    }

    /**
     * @param resultList the resultList to set
     */
    public void setResultList(List<?> resultList) {
        this.resultList = resultList;
    }

    public PagedList(long pageCount, int pageNumber, int maxRecords, List<?> resultList) {
        super();
        this.pageCount = pageCount;
        this.pageNumber = pageNumber;
        this.maxRecords = maxRecords;
        this.resultList = resultList;
    }
    
    public PagedList() {}

}
