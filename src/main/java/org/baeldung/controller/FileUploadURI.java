package org.baeldung.controller;

/**
 * @author MAX see https://studio.restlet.com/apis/local/sections/Companies
 *         https://hello-angularjs.appspot.com/searchtable
 */
public final class FileUploadURI {

    public static final String FILEINGESTOR_BASE_CONTEXT = "/questionnaire";

    public static final String POST_UPLOAD_MAPPING = "/fileupload";

    public static final String POST_MULTI_FILEUPLOAD_MAPPING = "/multiFileUpload";

    public static final String POST_STATIC_MULTI_FILEUPLOAD_MAPPING = "/static/multiFileUpload";
    
    public static final String GET_BATCH_STATUS_MAPPING = "/batchStatus";

    public static final String GET_FILE_ERRORS_MAPPING = "/fileErrors";

    public static final String POST_FILE_DOWNLOAD_MAPPING = "/fileDownload";
    
    public static final String POST_FORWARD_MAPPING = "/fileforward";
    
    private FileUploadURI() {
    }

}