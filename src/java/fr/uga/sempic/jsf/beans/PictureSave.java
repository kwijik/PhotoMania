/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.Picture;
import fr.uga.miashs.sempic.model.datalayer.AlbumDao;
import fr.uga.miashs.sempic.model.datalayer.PictureDao;
import fr.uga.miashs.sempic.model.datalayer.PictureStore;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;


@Named
@SessionScoped
public class PictureSave implements Serializable{

    private Part file;
      
    private PictureStore ps;
    
    @EJB
    private PictureDao dao;
    
    private Long albumId;
    
    @EJB
    AlbumDao albumDao;
    
    private static final Map <String, String> mimeTypes; // static - one obj for all
    
    static {
        Map <String, String> aMap = new HashMap <String, String>();
        aMap.put("image/png", "png");
        aMap.put("image/jpeg", "jpeg");
        mimeTypes = Collections.unmodifiableMap(aMap);
    }
    
    public Long getAlbumId(){
        if (albumId == null){
            return Long.valueOf(0);
        }
        return albumId;
    }
    
    public void setAlbumId(Long id) {
        this.albumId = id;
    }
    
    public PictureSave(){
        try {
            ps = new PictureStore();
        } catch (IOException ex) {
            Logger.getLogger(PictureSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Part getFile(){
        return file;
    }
    public void setFile(Part f){
        this.file = f;
    }
    
    public void save() throws Exception {
        try  {
            InputStream input = file.getInputStream();
            String mime = file.getContentType();
            
            if (mimeTypes.containsKey(mime)){
                
                String fileName = createSha1(input) + "." + mimeTypes.get(mime);
                input = file.getInputStream(); // mark(0) doesnt work, so we initialize again
            //  String fileName = "test.jpeg";
                ps.storePicture(PictureStore.UPLOADS.resolve(fileName),input);
                Picture p = new Picture();
                p.setName(fileName);
                p.setAlbum(albumDao.getById(albumId));
                dao.create(p);
            } 
            
        }
        catch (IOException e) {
        // Show faces message?
        }
    }
    
    
    private String createSha1(InputStream fis) throws IOException  {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
             int n = 0;
            byte[] buffer = new byte[8192];
            while (n != -1) {
                n = fis.read(buffer);
                if (n > 0) {
                    digest.update(buffer, 0, n);
                }
            }
            return  DatatypeConverter.printHexBinary(digest.digest());

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PictureSave.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

}
