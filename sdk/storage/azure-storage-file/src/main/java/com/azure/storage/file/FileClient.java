// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.storage.file;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.VoidResponse;
import com.azure.core.util.Context;
import com.azure.storage.common.IPRange;
import com.azure.storage.common.SASProtocol;
import com.azure.storage.common.Utility;
import com.azure.storage.common.credentials.SASTokenCredential;
import com.azure.storage.common.credentials.SharedKeyCredential;
import com.azure.storage.file.models.FileCopyInfo;
import com.azure.storage.file.models.FileDownloadInfo;
import com.azure.storage.file.models.FileHTTPHeaders;
import com.azure.storage.file.models.FileInfo;
import com.azure.storage.file.models.FileMetadataInfo;
import com.azure.storage.file.models.FileProperties;
import com.azure.storage.file.models.FileRange;
import com.azure.storage.file.models.FileUploadInfo;
import com.azure.storage.file.models.FileUploadRangeFromURLInfo;
import com.azure.storage.file.models.HandleItem;
import com.azure.storage.file.models.StorageException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.FileAlreadyExistsException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Map;

/**
 * This class provides a client that contains all the operations for interacting files under Azure Storage File Service.
 * Operations allowed by the client are creating, uploading, copying, listing, downloading, and deleting files.
 *
 * <p><strong>Instantiating a synchronous File Client</strong></p>
 *
 * {@codesnippet com.azure.storage.file.fileClient.instantiation}
 *
 * <p>View {@link FileClientBuilder this} for additional ways to construct the client.</p>
 *
 * @see FileClientBuilder
 * @see FileAsyncClient
 * @see SharedKeyCredential
 * @see SASTokenCredential
 */
public class FileClient {
    private final FileAsyncClient fileAsyncClient;

    /**
     * Creates a FileClient that wraps a FileAsyncClient and blocks requests.
     *
     * @param fileAsyncClient FileAsyncClient that is used to send requests
     */
    FileClient(FileAsyncClient fileAsyncClient) {
        this.fileAsyncClient = fileAsyncClient;
    }

    /**
     * Get the url of the storage file client.
     * @return the URL of the storage file client.
     * @throws RuntimeException If the file is using a malformed URL.
     */
    public URL getFileUrl() {
        return fileAsyncClient.getFileUrl();
    }

    /**
     * Creates a file in the storage account and returns a response of {@link FileInfo} to interact with it.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Create the file with length of 1024 bytes, some headers and metadata.</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.create}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/create-file">Azure Docs</a>.</p>
     *
     * @param maxSize The maximum size in bytes for the file, up to 1 TiB.
     * @return The {@link FileInfo file info}
     * @throws StorageException If the file has already existed, the parent directory does not exist or fileName is an invalid resource name.
     */
    public FileInfo create(long maxSize) {
        return createWithResponse(maxSize, null, null, null, null, null, Context.NONE).getValue();
    }

    /**
     * Creates a file in the storage account and returns a response of FileInfo to interact with it.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Create the file with length of 1024 bytes, some headers, file smb properties and metadata.</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.createWithResponse#long-filehttpheaders-filesmbproperties-string-map-duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/create-file">Azure Docs</a>.</p>
     *
     * @param maxSize The maximum size in bytes for the file, up to 1 TiB.
     * @param httpHeaders The user settable file http headers.
     * @param smbProperties The user settable file smb properties.
     * @param filePermission The file permission of the file.
     * @param metadata Optional name-value pairs associated with the file as metadata.
     * @see <a href="https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/">C# identifiers</a>
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the {@link FileInfo file info} and the status of creating the file.
     * @throws StorageException If the directory has already existed, the parent directory does not exist or directory is an invalid resource name.
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileInfo> createWithResponse(long maxSize, FileHTTPHeaders httpHeaders, FileSmbProperties smbProperties,
        String filePermission, Map<String, String> metadata, Duration timeout, Context context) {
        Mono<Response<FileInfo>> response = fileAsyncClient.createWithResponse(maxSize, httpHeaders, smbProperties, filePermission, metadata, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Copies a blob or file to a destination file within the storage account.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Copy file from source getDirectoryUrl to the {@code resourcePath} </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.startCopy#string-map}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/copy-file">Azure Docs</a>.</p>
     *
     * @param sourceUrl Specifies the URL of the source file or blob, up to 2 KB in length.
     * @param metadata Optional name-value pairs associated with the file as metadata. Metadata names must adhere to the naming rules.
     * @see <a href="https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/">C# identifiers</a>
     * @return The {@link FileCopyInfo file copy info}
     */
    public FileCopyInfo startCopy(String sourceUrl, Map<String, String> metadata) {
        return startCopyWithResponse(sourceUrl, metadata, null, Context.NONE).getValue();
    }

    /**
     * Copies a blob or file to a destination file within the storage account.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Copy file from source getDirectoryUrl to the {@code resourcePath} </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.startCopyWithResponse#string-map-duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/copy-file">Azure Docs</a>.</p>
     *
     * @param sourceUrl Specifies the URL of the source file or blob, up to 2 KB in length.
     * @param metadata Optional name-value pairs associated with the file as metadata. Metadata names must adhere to the naming rules.
     * @see <a href="https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/">C# identifiers</a>
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the {@link FileCopyInfo file copy info} and the status of copying the file.
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileCopyInfo> startCopyWithResponse(String sourceUrl, Map<String, String> metadata,
                                                        Duration timeout, Context context) {
        Mono<Response<FileCopyInfo>> response = fileAsyncClient.startCopyWithResponse(sourceUrl, metadata, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Aborts a pending Copy File operation, and leaves a destination file with zero length and full metadata.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Abort copy file from copy id("someCopyId") </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.abortCopy#string}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/abort-copy-file">Azure Docs</a>.</p>
     *
     * @param copyId Specifies the copy id which has copying pending status associate with it.
     */
    public void abortCopy(String copyId) {
        abortCopyWithResponse(copyId, null, Context.NONE);
    }

    /**
     * Aborts a pending Copy File operation, and leaves a destination file with zero length and full metadata.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Abort copy file from copy id("someCopyId") </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.abortCopyWithResponse#string-duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/abort-copy-file">Azure Docs</a>.</p>
     *
     * @param copyId Specifies the copy id which has copying pending status associate with it.
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the status of aborting copy the file.
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public VoidResponse abortCopyWithResponse(String copyId, Duration timeout, Context context) {
        Mono<VoidResponse> response = fileAsyncClient.abortCopyWithResponse(copyId, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Downloads a file from the system, including its metadata and properties into a file specified by the path.
     *
     * <p>The file will be created and must not exist, if the file already exists a {@link FileAlreadyExistsException}
     * will be thrown.</p>
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Download the file to current folder. </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.downloadToFile#string}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/get-file">Azure Docs</a>.</p>
     *
     * @param downloadFilePath The path where store the downloaded file
     */
    public void downloadToFile(String downloadFilePath) {
        downloadToFile(downloadFilePath, null);
    }

    /**
     * Downloads a file from the system, including its metadata and properties into a file specified by the path.
     *
     * <p>The file will be created and must not exist, if the file already exists a {@link FileAlreadyExistsException}
     * will be thrown.</p>
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Download the file from 1024 to 2048 bytes to current folder. </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.downloadToFile#string-filerange}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/get-file">Azure Docs</a>.</p>
     *
     * @param downloadFilePath The path where store the downloaded file
     * @param range Optional byte range which returns file data only from the specified range.
     */
    public void downloadToFile(String downloadFilePath, FileRange range) {
        fileAsyncClient.downloadToFile(downloadFilePath, range).block();
    }

    /**
     * Downloads a file from the system, including its metadata and properties
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Download the file with its metadata and properties. </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.downloadWithProperties}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/get-file">Azure Docs</a>.</p>
     *
     * @return The {@link FileDownloadInfo file download info}
     */
    public FileDownloadInfo downloadWithProperties() {
        return downloadWithPropertiesWithResponse(null, null, null, Context.NONE).getValue();
    }

    /**
     * Downloads a file from the system, including its metadata and properties
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Download the file from 1024 to 2048 bytes with its metadata and properties and without the contentMD5. </p>
     *
     * {@codesnippet com.azure.storage.file.FileClient.downloadWithPropertiesWithResponse#FileRange-Boolean-Duration-Context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/get-file">Azure Docs</a>.</p>
     *
     * @param range Optional byte range which returns file data only from the specified range.
     * @param rangeGetContentMD5 Optional boolean which the service returns the MD5 hash for the range when it sets to true, as long as the range is less than or equal to 4 MB in size.
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the {@link FileDownloadInfo file download info} headers and response status code
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileDownloadInfo> downloadWithPropertiesWithResponse(FileRange range, Boolean rangeGetContentMD5, Duration timeout, Context context) {
        Mono<Response<FileDownloadInfo>> response = fileAsyncClient.downloadWithPropertiesWithResponse(range, rangeGetContentMD5, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Deletes the file associate with the client.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Delete the file</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.delete}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/delete-file2">Azure Docs</a>.</p>
     *
     * @throws StorageException If the directory doesn't exist or the file doesn't exist.
     */
    public void delete() {
        deleteWithResponse(null, Context.NONE);
    }


    /**
     * Deletes the file associate with the client.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Delete the file</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.deleteWithResponse#duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/delete-file2">Azure Docs</a>.</p>
     *
     * @return A response that only contains headers and response status code
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @throws StorageException If the directory doesn't exist or the file doesn't exist.
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public VoidResponse deleteWithResponse(Duration timeout, Context context) {
        Mono<VoidResponse> response = fileAsyncClient.deleteWithResponse(context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Retrieves the properties of the storage account's file.
     * The properties includes file metadata, last modified date, is server encrypted, and eTag.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Retrieve file properties</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.getProperties}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/get-file-properties">Azure Docs</a>.</p>
     *
     * @return {@link FileProperties Storage file properties}
     */
    public FileProperties getProperties() {
        return getPropertiesWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Retrieves the properties of the storage account's file.
     * The properties includes file metadata, last modified date, is server encrypted, and eTag.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Retrieve file properties</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.getPropertiesWithResponse#duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/get-file-properties">Azure Docs</a>.</p>
     *
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the {@link FileProperties Storage file properties} with headers and status code
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileProperties> getPropertiesWithResponse(Duration timeout, Context context) {
        Mono<Response<FileProperties>> response = fileAsyncClient.getPropertiesWithResponse(context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Sets the user-defined httpHeaders to associate to the file.
     *
     * <p>If {@code null} is passed for the httpHeaders it will clear the httpHeaders associated to the file.</p>
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Set the httpHeaders of contentType of "text/plain"</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setProperties#long-filehttpheaders-filesmbproperties-string}
     *
     * <p>Clear the httpHeaders of the file and preserve the SMB properties</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setProperties#long-filehttpheaders-filesmbproperties-string.clearHttpHeaderspreserveSMBProperties}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/set-file-properties">Azure Docs</a>.</p>
     *
     * @param newFileSize New file size of the file
     * @param httpHeaders The user settable file http headers.
     * @param smbProperties The user settable file smb properties.
     * @param filePermission The file permission of the file
     * @return The {@link FileInfo file info}
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     */
    public FileInfo setProperties(long newFileSize, FileHTTPHeaders httpHeaders, FileSmbProperties smbProperties,
        String filePermission) {
        return setPropertiesWithResponse(newFileSize, httpHeaders, smbProperties, filePermission, null, Context.NONE).getValue();
    }

    /**
     * Sets the user-defined httpHeaders to associate to the file.
     *
     * <p>If {@code null} is passed for the httpHeaders it will clear the httpHeaders associated to the file.</p>
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Set the httpHeaders of contentType of "text/plain"</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setPropertiesWithResponse#long-filehttpheaders-filesmbproperties-string-duration-Context}
     *
     * <p>Clear the httpHeaders of the file and preserve the SMB properties</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setPropertiesWithResponse#long-filehttpheaders-filesmbproperties-string-duration-Context.clearHttpHeaderspreserveSMBProperties}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/set-file-properties">Azure Docs</a>.</p>
     *
     * @param newFileSize New file size of the file
     * @param httpHeaders The user settable file http headers.
     * @param smbProperties The user settable file smb properties.
     * @param filePermission The file permission of the file
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return Response containing the {@link FileInfo file info} with headers and status code
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileInfo> setPropertiesWithResponse(long newFileSize, FileHTTPHeaders httpHeaders,
        FileSmbProperties smbProperties, String filePermission, Duration timeout, Context context) {
        Mono<Response<FileInfo>> response = fileAsyncClient.setPropertiesWithResponse(newFileSize, httpHeaders, smbProperties, filePermission, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Sets the user-defined metadata to associate to the file.
     *
     * <p>If {@code null} is passed for the metadata it will clear the metadata associated to the file.</p>
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Set the metadata to "file:updatedMetadata"</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setMetadata#map}
     *
     * <p>Clear the metadata of the file</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setMetadata#map.clearMetadata}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/set-file-metadata">Azure Docs</a>.</p>
     *
     * @param metadata Options.Metadata to set on the file, if null is passed the metadata for the file is cleared
     * @return The {@link FileMetadataInfo file meta info}
     * @throws StorageException If the file doesn't exist or the metadata contains invalid keys
     */
    public FileMetadataInfo setMetadata(Map<String, String> metadata) {
        return setMetadataWithResponse(metadata, null, Context.NONE).getValue();
    }

    /**
     * Sets the user-defined metadata to associate to the file.
     *
     * <p>If {@code null} is passed for the metadata it will clear the metadata associated to the file.</p>
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Set the metadata to "file:updatedMetadata"</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setMetadataWithResponse#map-duration-context}
     *
     * <p>Clear the metadata of the file</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.setMetadataWithResponse#map-duration-context.clearMetadata}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/set-file-metadata">Azure Docs</a>.</p>
     *
     * @param metadata Options.Metadata to set on the file, if null is passed the metadata for the file is cleared
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return Response containing the {@link FileMetadataInfo file meta info} with headers and status code
     * @throws StorageException If the file doesn't exist or the metadata contains invalid keys
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileMetadataInfo> setMetadataWithResponse(Map<String, String> metadata, Duration timeout, Context context) {
        Mono<Response<FileMetadataInfo>> response = fileAsyncClient.setMetadataWithResponse(metadata, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Uploads a range of bytes to the beginning of a file in storage file service. Upload operations performs an in-place write on the specified file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Upload data "default" to the file in Storage File Service. </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.upload#bytebuffer-long}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs</a>.</p>
     *
     * @param data The data which will upload to the storage file.
     * @param length Specifies the number of bytes being transmitted in the request body. When the FileRangeWriteType is set to clear, the value of this header must be set to zero..
     * @return The {@link FileUploadInfo file upload info}
     * @throws StorageException If you attempt to upload a range that is larger than 4 MB, the service returns status code 413 (Request Entity Too Large)
     */
    public FileUploadInfo upload(ByteBuffer data, long length) {
        return uploadWithResponse(data, length, null, Context.NONE).getValue();
    }

   /**
     * Uploads a range of bytes to the beginning of a file in storage file service. Upload operations performs an in-place write on the specified file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Upload "default" to the file. </p>
     *
     * {@codesnippet com.azure.storage.file.FileClient.uploadWithResponse#ByteBuffer-long-Duration-Context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs</a>.</p>
     *
     * @param data The data which will upload to the storage file.
     * @param length Specifies the number of bytes being transmitted in the request body. When the FileRangeWriteType is set to clear, the value of this header must be set to zero.
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return The {@link FileUploadInfo file upload info}
     * @throws StorageException If you attempt to upload a range that is larger than 4 MB, the service returns status code 413 (Request Entity Too Large)
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileUploadInfo> uploadWithResponse(ByteBuffer data, long length, Duration timeout, Context context) {
        Mono<Response<FileUploadInfo>> response = fileAsyncClient.uploadWithResponse(Flux.just(data), length, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Uploads a range of bytes to specific of a file in storage file service. Upload operations performs an in-place write on the specified file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Upload data "default" starting from 1024. </p>
     *
     * {@codesnippet com.azure.storage.file.FileClient.uploadWithResponse#ByteBuffer-long-long-Duration-Context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs</a>.</p>
     *
     * @param data The data which will upload to the storage file.
     * @param length Specifies the number of bytes being transmitted in the request body.
     * @param offset Optional starting point of the upload range. It will start from the beginning if it is {@code null}
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the {@link FileUploadInfo file upload info} with headers and response status code
     * @throws StorageException If you attempt to upload a range that is larger than 4 MB, the service returns status code 413 (Request Entity Too Large)
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileUploadInfo> uploadWithResponse(ByteBuffer data, long length, long offset, Duration timeout, Context context) {
        Mono<Response<FileUploadInfo>> response = fileAsyncClient.uploadWithResponse(Flux.just(data), length, offset, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Uploads a range of bytes from one file to another file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Upload a number of bytes from a file at defined source and destination offsets </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.uploadRangeFromURL#long-long-long-uri}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs</a>.</p>
     *
     * @param length Specifies the number of bytes being transmitted in the request body.
     * @param destinationOffset Starting point of the upload range on the destination.
     * @param sourceOffset Starting point of the upload range on the source.
     * @param sourceURI Specifies the URL of the source file.
     * @return The {@link FileUploadRangeFromURLInfo file upload range from url info}
     */
    // TODO: (gapra) Fix put range from URL link. Service docs have not been updated to show this API
    public FileUploadRangeFromURLInfo uploadRangeFromURL(long length, long destinationOffset, long sourceOffset,
        URI sourceURI) {
        return uploadRangeFromURLWithResponse(length, destinationOffset, sourceOffset, sourceURI, null, Context.NONE).getValue();
    }

    /**
     * Uploads a range of bytes from one file to another file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Upload a number of bytes from a file at defined source and destination offsets </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.uploadRangeFromURLWithResponse#long-long-long-uri-duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs</a>.</p>
     *
     * @param length Specifies the number of bytes being transmitted in the request body.
     * @param destinationOffset Starting point of the upload range on the destination.
     * @param sourceOffset Starting point of the upload range on the source.
     * @param sourceURI Specifies the URL of the source file.
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the {@link FileUploadRangeFromURLInfo file upload range from url info} with headers
     * and response status code.
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    // TODO: (gapra) Fix put range from URL link. Service docs have not been updated to show this API
    public Response<FileUploadRangeFromURLInfo> uploadRangeFromURLWithResponse(long length, long destinationOffset,
        long sourceOffset, URI sourceURI, Duration timeout, Context context) {
        Mono<Response<FileUploadRangeFromURLInfo>> response = fileAsyncClient.uploadRangeFromURLWithResponse(length,
            destinationOffset, sourceOffset, sourceURI, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Clears a range of bytes to specific of a file in storage file service. Clear operations performs an in-place write on the specified file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Clears the first 1024 bytes. </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.clearRange#long}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs</a>.</p>
     *
     * @param length Specifies the number of bytes being cleared.
     * @return The {@link FileUploadInfo file upload info}
     */
    public FileUploadInfo clearRange(long length) {
        return clearRangeWithResponse(length, 0, null, Context.NONE).getValue();
    }

    /**
     * Clears a range of bytes to specific of a file in storage file service. Upload operations performs an in-place write on the specified file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Clear the range starting from 1024 with length of 1024. </p>
     *
     * {@codesnippet com.azure.storage.file.FileClient.clearRangeWithResponse#long-long-Duration-Context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs</a>.</p>
     *
     * @param length Specifies the number of bytes being transmitted in the request body.
     * @param offset Optional starting point of the upload range. It will start from the beginning if it is {@code null}
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return A response containing the {@link FileUploadInfo file upload info} with headers and response status code
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public Response<FileUploadInfo> clearRangeWithResponse(long length, long offset, Duration timeout, Context context) {
        Mono<Response<FileUploadInfo>> response = fileAsyncClient.clearRangeWithResponse(length, offset, context);
        return Utility.blockWithOptionalTimeout(response, timeout);
    }

    /**
     * Uploads file to storage file service.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Upload the file from the source file path. </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.uploadFromFile#string}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/create-file">Azure Docs Create File</a>
     * and
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/put-range">Azure Docs Upload</a>.</p>
     *
     * @param uploadFilePath The path where store the source file to upload
     */
    public void uploadFromFile(String uploadFilePath) {
        fileAsyncClient.uploadFromFile(uploadFilePath).block();
    }

    /**
     * List of valid ranges for a file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>List all ranges for the file client.</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.listRanges}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/list-ranges">Azure Docs</a>.</p>
     *
     * @return {@link FileRange ranges} in the files.
     */
    public PagedIterable<FileRange> listRanges() {
        return listRanges(null, null, null);
    }

    /**
     * List of valid ranges for a file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>List all ranges within the file range from 1KB to 2KB.</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.listRanges#filerange-duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/list-ranges">Azure Docs</a>.</p>
     *
     * @param range Optional byte range which returns file data only from the specified range.
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return {@link FileRange ranges} in the files that satisfy the requirements
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public PagedIterable<FileRange> listRanges(FileRange range, Duration timeout, Context context) {
        return new PagedIterable<>(fileAsyncClient.listRangesWithOptionalTimeout(range, timeout, context));
    }

    /**
     * List of open handles on a file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>List all handles for the file client.</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.listHandles}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/list-handles">Azure Docs</a>.</p>
     *
     * @return {@link HandleItem handles} in the files that satisfy the requirements
     */
    public PagedIterable<HandleItem> listHandles() {
        return listHandles(null, null, Context.NONE);
    }

    /**
     * List of open handles on a file.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>List 10 handles for the file client.</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.listHandles#integer-duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/list-handles">Azure Docs</a>.</p>
     *
     * @param maxResults Optional max number of results returned per page
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return {@link HandleItem handles} in the file that satisfy the requirements
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public PagedIterable<HandleItem> listHandles(Integer maxResults, Duration timeout, Context context) {
        return new PagedIterable<>(fileAsyncClient.listHandlesWithOptionalTimeout(maxResults, timeout, context));
    }

    /**
     * Closes a handle or handles opened on a file at the service. It is intended to be used alongside {@link FileClient#listHandles()} (Integer)} .
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Force close handles with handles returned by list handles in recursive.</p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.forceCloseHandles#string-duration-context}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/force-close-handles">Azure Docs</a>.</p>
     *
     * @param handleId Specifies the handle ID to be closed. Use an asterisk ('*') as a wildcard string to specify all handles.
     * @param timeout An optional timeout applied to the operation. If a response is not returned before the timeout concludes a {@link RuntimeException} will be thrown.
     * @param context Additional context that is passed through the Http pipeline during the service call.
     * @return The counts of number of handles closed
     * @throws RuntimeException if the operation doesn't complete before the timeout concludes.
     */
    public PagedIterable<Integer> forceCloseHandles(String handleId, Duration timeout, Context context) {
        // TODO: Will change the return type to how many handles have been closed. Implement one more API to force close all handles.
        // TODO: @see <a href="https://github.com/Azure/azure-sdk-for-java/issues/4525">Github Issue 4525</a>
        return new PagedIterable<>(fileAsyncClient.forceCloseHandlesWithOptionalTimeout(handleId, timeout, context));
    }

    /**
     * Get snapshot id which attached to {@link FileClient}.
     * Return {@code null} if no snapshot id attached.
     *
     * <p><strong>Code Samples</strong></p>
     *
     * <p>Get the share snapshot id. </p>
     *
     * {@codesnippet com.azure.storage.file.fileClient.getShareSnapshotId}
     *
     * @return The snapshot id which is a unique {@code DateTime} value that identifies the share snapshot to its base share.
     */
    public String getShareSnapshotId() {
        return fileAsyncClient.getShareSnapshotId();
    }

    /**
     * Generates a SAS token with the specified parameters
     *
     * @param expiryTime The {@code OffsetDateTime} expiry time for the SAS
     * @param permissions The {@code FileSASPermission} permission for the SAS
     * @return A string that represents the SAS token
     */
    public String generateSAS(OffsetDateTime expiryTime, FileSASPermission permissions) {
        return this.fileAsyncClient.generateSAS(permissions, expiryTime);
    }

    /**
     * Generates a SAS token with the specified parameters
     *
     * @param identifier The {@code String} name of the access policy on the share this SAS references if any
     * @return A string that represents the SAS token
     */
    public String generateSAS(String identifier) {
        return this.fileAsyncClient.generateSAS(identifier);
    }

    /**
     * Generates a SAS token with the specified parameters
     *
     * @param identifier The {@code String} name of the access policy on the share this SAS references if any
     * @param permissions The {@code FileSASPermission} permission for the SAS
     * @param expiryTime The {@code OffsetDateTime} expiry time for the SAS
     * @param startTime An optional {@code OffsetDateTime} start time for the SAS
     * @param version An optional {@code String} version for the SAS
     * @param sasProtocol An optional {@code SASProtocol} protocol for the SAS
     * @param ipRange An optional {@code IPRange} ip address range for the SAS
     * @return A string that represents the SAS token
     */
    public String generateSAS(String identifier, FileSASPermission permissions, OffsetDateTime expiryTime,
        OffsetDateTime startTime, String version, SASProtocol sasProtocol, IPRange ipRange) {
        return this.fileAsyncClient.generateSAS(identifier, permissions, expiryTime, startTime, version, sasProtocol,
            ipRange);
    }

    /**
     * Generates a SAS token with the specified parameters
     *
     * <p><strong>Code Samples</strong></p>
     *
     * {@codesnippet com.azure.storage.file.FileClient.generateSAS#String-FileSASPermission-OffsetDateTime-OffsetDateTime-String-SASProtocol-IPRange-String-String-String-String-String}
     *
     * <p>For more information, see the
     * <a href="https://docs.microsoft.com/en-us/rest/api/storageservices/create-service-sas">Azure Docs</a>.</p>
     *
     * @param identifier The {@code String} name of the access policy on the share this SAS references if any
     * @param permissions The {@code FileSASPermission} permission for the SAS
     * @param expiryTime The {@code OffsetDateTime} expiry time for the SAS
     * @param startTime An optional {@code OffsetDateTime} start time for the SAS
     * @param version An optional {@code String} version for the SAS
     * @param sasProtocol An optional {@code SASProtocol} protocol for the SAS
     * @param ipRange An optional {@code IPRange} ip address range for the SAS
     * @param cacheControl An optional {@code String} cache-control header for the SAS.
     * @param contentDisposition An optional {@code String} content-disposition header for the SAS.
     * @param contentEncoding An optional {@code String} content-encoding header for the SAS.
     * @param contentLanguage An optional {@code String} content-language header for the SAS.
     * @param contentType An optional {@code String} content-type header for the SAS.
     * @return A string that represents the SAS token
     */
    public String generateSAS(String identifier, FileSASPermission permissions, OffsetDateTime expiryTime,
        OffsetDateTime startTime, String version, SASProtocol sasProtocol, IPRange ipRange, String cacheControl,
        String contentDisposition, String contentEncoding, String contentLanguage, String contentType) {
        return this.fileAsyncClient.generateSAS(identifier, permissions, expiryTime, startTime, version, sasProtocol,
            ipRange, cacheControl, contentDisposition, contentEncoding, contentLanguage, contentType);
    }
}

