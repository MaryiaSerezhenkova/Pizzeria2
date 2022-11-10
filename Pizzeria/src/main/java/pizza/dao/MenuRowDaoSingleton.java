package pizza.dao;

import pizza.dao.api.DataSourceCreator;
import pizza.dao.api.IMenuRowDao;

public class MenuRowDaoSingleton {
	  private MenuRowDao menuRowDao;
	    private volatile static MenuRowDaoSingleton instance;

	    public MenuRowDaoSingleton() {
	        try{
	            this.menuRowDao = (MenuRowDao) new MenuRowDao(DataSourceCreator.getInstance());
	        } catch (Exception e){
	            throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
	        }
	    }

	    public static IMenuRowDao getInstance() {
	        if(instance == null){
	            synchronized (MenuRowDaoSingleton.class){
	                if(instance == null){
	                    instance = new MenuRowDaoSingleton();
	                }
	            }
	        }
	        return instance.menuRowDao;
	    }
	}


