/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author Desarrollo99
 */
public class main {

    private final static String archivosReportes = System.getProperty("file.separator").concat("REPORTESSISTEMAS");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        buscaRutaArchivoss(System.getProperty("user.dir"), archivosReportes);
    }

    private static String buscaRutaArchivoss(String ruta, String pathBuscar) {
        File file = new File(ruta);
        String path = "";

        if (file.exists()) {
            Path root = file.toPath();
            if (root.getRoot().toString().equalsIgnoreCase(ruta)) {
                ruta = ruta.replace(System.getProperty("file.separator"), "").concat(pathBuscar);
            }
            File config = new File(ruta.concat(pathBuscar));
            if (config.exists()) {
                return config.getAbsolutePath();
            } else {
                if (file.getParentFile() != null) {
                    path = buscaRutaArchivoss(file.getParentFile().getAbsolutePath(), pathBuscar);
                }
            }
        }
        return path;
    }

}
