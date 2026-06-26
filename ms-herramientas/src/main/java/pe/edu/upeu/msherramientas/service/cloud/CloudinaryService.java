package pe.edu.upeu.msherramientas.service.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        // NOTA: Estas credenciales deben ser reemplazadas por las que
        // les dio el Dashboard de Cloudinary.
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dz64gzrk1",                   // "tu_cloud_name"
                "api_key", "465483216322983",                        // "tu_api_key"
                "api_secret", "wHw_0n32j1BMKVxrICZZVk5misg"          // "tu_api_secret"
        ));
    }

    public String subirImagen(MultipartFile archivo) throws Exception {
        // El nombre del archivo se construye como pidió el profe: nombre + timestamp
        String nombrePublico = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();

        Map uploadResult = cloudinary.uploader().upload(archivo.getBytes(),
                ObjectUtils.asMap("public_id", nombrePublico));

        return uploadResult.get("url").toString();
    }
}
