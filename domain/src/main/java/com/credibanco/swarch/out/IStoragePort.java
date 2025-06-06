package com.credibanco.swarch.out;



import com.credibanco.swarch.exceptions.StorageException;
import com.credibanco.swarch.util.RequestFile;

import java.io.InputStream;

/**
 * Interfaz para el servicio de almacenamiento.
 * <p>
 * Define métodos para interactuar con el almacenamiento de blobs.
 * </p>
 */
public interface IStoragePort {


    void uploadFile(String blobName, RequestFile file) throws StorageException;

    /**
     * Descarga un archivo desde el almacenamiento de blobs.
     *
     * @param blobName el nombre del blob en el contenedor
     * @return un {@link InputStream} con el contenido del blob
     * @throws StorageException si ocurre un error durante la descarga
     */
    InputStream downloadFile(String blobName) throws StorageException;

    /**
     * Elimina un archivo del almacenamiento de blobs.
     *
     * @param blobName el nombre del blob en el contenedor
     * @throws StorageException si ocurre un error durante la eliminación
     */
    void deleteFile(String blobName) throws StorageException;

    /**
     * Verifica si un blob existe en el contenedor.
     *
     * @param blobName el nombre del blob en el contenedor
     * @return {@code true} si el blob existe, {@code false} en caso contrario
     * @throws StorageException si ocurre un error durante la verificación
     */
    boolean exists(String blobName) throws StorageException;
}
