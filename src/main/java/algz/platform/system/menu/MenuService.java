package algz.platform.system.menu;



import java.util.List;
import java.util.Set;


public interface MenuService {

    List<Menu> findAll();
    List<Menu> findAll(String requestPath);

}
