package com.unicomer.demo.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.demo.controller.util.JsfUtil;
import com.unicomer.demo.controller.util.JsfUtil.PersistAction;
import com.unicomer.demo.dao.BuyingProviderClient;

@Named("unicomerVendorController")
@ManagedBean
@SessionScoped
public class UnicomerVendorController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8523785824657134794L;
	private BuyingProviderClient buyingProviderClient = new BuyingProviderClient();
    private List<UnicomerVendor> items = null;
    private UnicomerVendor selected;

    public UnicomerVendorController() {
    }

    public UnicomerVendor getSelected() {
        return selected;
    }

    public void setSelected(UnicomerVendor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BuyingProviderClient getFacade() {
        return buyingProviderClient;
    }

    public UnicomerVendor prepareCreate() {
        selected = new UnicomerVendor();
        initializeEmbeddableKey();
        return selected;
    }
    
    public UnicomerVendor getPrepareCreate() {
        return prepareCreate();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UnicomerVendorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UnicomerVendorUpdated"));
    }
    
    public void getDestroy() {
        destroy();
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UnicomerVendorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UnicomerVendor> getItems() {
    	Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Inicio de consulta de datos");
        if (items == null) {
            items = getFacade().findAll();
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Fin de consulta de datos: "+items.size());
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UnicomerVendor getUnicomerVendor(java.math.BigDecimal id) {
        return getFacade().find(id.toString());
    }

    public List<UnicomerVendor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UnicomerVendor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UnicomerVendor.class)
    public static class UnicomerVendorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UnicomerVendorController controller = (UnicomerVendorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "unicomerVendorController");
            return controller.getUnicomerVendor(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UnicomerVendor) {
                UnicomerVendor o = (UnicomerVendor) object;
                return getStringKey(o.getVendorId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UnicomerVendor.class.getName()});
                return null;
            }
        }

    }

}
