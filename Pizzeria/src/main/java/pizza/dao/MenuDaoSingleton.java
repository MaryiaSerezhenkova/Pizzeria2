package pizza.dao;

import pizza.dao.api.DataSourceCreator;
import pizza.dao.api.IMenuDao;

public class MenuDaoSingleton {
    private MenuDao menuDao;
    private volatile static MenuDaoSingleton instance;

    public MenuDaoSingleton() {
        try{
            this.menuDao = (MenuDao) new MenuDao(DataSourceCreator.getInstance());
        } catch (Exception e){
            throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
        }
    }

    public static IMenuDao getInstance() {
        if(instance == null){
            synchronized (MenuDaoSingleton.class){
                if(instance == null){
                    instance = new MenuDaoSingleton();
                }
            }
        }
        return instance.menuDao;
    }
}
