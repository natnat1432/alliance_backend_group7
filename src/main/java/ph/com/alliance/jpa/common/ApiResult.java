package ph.com.alliance.jpa.common;

import java.util.List;

/**
 * The ApiResult class wraps the response data for the API Request made to the
 * server.
 */
public class ApiResult {
    private enum Status {
        SUCCESS, ERROR
    }

    public static enum MESSAGE_TYPE {
        INSERT, UPDATE, DELETE, RETRIEVE, DUPLICATE_KEY
    }

    public Object data;
    public Status status;
    public String message;
    public String stackTrace;
    public List<String> messageList;

    /**
     * Assigns the local variable with the passed parameters
     * 
     * @param status
     *            This is the status of the result SUCCESS or ERROR
     * @param model
     *            This is the instance of the object model
     * 
     */
    public ApiResult(Status status, Object model) {
        this.status = status;
        this.data = model;
    }

    /**
     * Assigns the local variable with the passed parameters
     * 
     * @param status
     *            This is the status of the result SUCCESS or ERROR
     * @param message
     *            This is the string message of the response
     * 
     */
    public ApiResult(Status status, String strMessage) {
        this.status = status;
        this.message = strMessage;
    }
    
    /**
     * Assigns the local variable with the passed parameters
     * 
     * @param status
     *            This is the status of the result SUCCESS or ERROR
     * @param message
     *            This is the list of string message of the response
     * 
     */
    public ApiResult(Status status, List<String> strMessage) {
        this.status = status;
        this.messageList = strMessage;
    }

    /**
     * Assigns the local variable with the passed parameters
     * 
     * @param status
     *            This is the status of the result SUCCESS or ERROR
     * @param model
     *            This is the instance of the object model
     * @param message
     *            This is the string message of the response
     * 
     */
    public ApiResult(Status status, Object model, String strMessage) {
        this.status = status;
        this.data = model;
        this.message = strMessage;
    }

    /**
     * Returns a new ApiResult class given the parameter
     * 
     * @param message
     *            This is the string message of the response
     * @return A new ApiResult instance with success status and message
     * 
     */
    public static ApiResult CreateSuccess(String strMessage) {
        return new ApiResult(Status.SUCCESS, strMessage);
    }

    /**
     * Returns a new ApiResult class given the parameter
     * 
     * @param model
     *            This is the instance of the object model
     * @return A new ApiResult instance with success status and model
     * 
     */
    public static ApiResult CreateSuccess(Object model) {
        return new ApiResult(Status.SUCCESS, model);
    }

    /**
     * Returns a new ApiResult class given the parameter
     * 
     * @param model
     *            This is the instance of the object model
     * @param message
     *            This is the string message of the response
     * @return A new ApiResult instance with success status, model, and message
     * 
     */
    public static ApiResult CreateSuccess(Object model, String strMessage) {
        return new ApiResult(Status.SUCCESS, model, strMessage);
    }
    
    public static ApiResult CreateSuccess( String strFunctionType, MESSAGE_TYPE strOperationType) {
        return new ApiResult(Status.SUCCESS, createMessage(strFunctionType, strOperationType, true));
    }

    public static ApiResult CreateSuccess(Object model, String strFunctionType, MESSAGE_TYPE strOperationType) {
        return new ApiResult(Status.SUCCESS, model, createMessage(strFunctionType, strOperationType, true));
    }

    /**
     * Returns a new ApiResult class given the parameter
     * 
     * @param message
     *            This is the string message of the response
     * @return A new ApiResult instance with error status and message
     * 
     */
    public static ApiResult CreateError(String strMessage) {
        return new ApiResult(Status.ERROR, strMessage);
    }
    
    public static ApiResult CreateError(List<String> strMessage) {
        return new ApiResult(Status.ERROR, strMessage);
    }

    public static ApiResult CreateError(String strFunctionType, MESSAGE_TYPE strOperationType) {
        return new ApiResult(Status.ERROR, createMessage(strFunctionType, strOperationType, false));
    }

    /**
     * Returns a new ApiResult class given the parameter
     * 
     * @param model
     *            This is the instance of the object model
     * @param message
     *            This is the string message of the response
     * @return A new ApiResult instance with error status and message
     * 
     */
    public static ApiResult CreateError(Object model, String message) {
        return new ApiResult(Status.ERROR, model, message);
    }

    private static String createMessage(String strFunction, MESSAGE_TYPE messageType, boolean isSuccess) {
        String strMessage = "[" + strFunction + "]";

        if (messageType == MESSAGE_TYPE.DUPLICATE_KEY) {
            return strMessage + " ";
        }

        switch (messageType) {
            case INSERT:
                strMessage += " add ";
                break;
            case UPDATE:
                strMessage += " update ";
                break;
            case DELETE:
                strMessage +=  " delete ";
                break;
            case RETRIEVE:
                strMessage +=  " retrieve ";
                break;
            default:
                strMessage += " retrieve ";
                break;
        }

        return strMessage + (isSuccess ? "successful": "failed");
    }

    public static ApiResult CreateError(String stackTrace, String strFunctionName, MESSAGE_TYPE retrieve) {
        return new ApiResult(Status.ERROR, stackTrace, createMessage(strFunctionName, retrieve, false));
    }
    
    public ApiResult(Status status, String stackTrace, String strMessage) {
        this.status = status;
        this.stackTrace = stackTrace;
        this.message = strMessage;
    }

}
