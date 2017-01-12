package com.unicomer.demo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.unicomer.demo.common.entity.Vendor;
import com.unicomer.demo.controller.util.JsfUtil;
import com.unicomer.demo.controller.util.JsfUtil.PersistAction;
import com.unicomer.demo.dao.BuyingProviderClient;

@SuppressWarnings("serial")
@Named("vendorController")
@SessionScoped
public class VendorController implements Serializable {
	
    private BuyingProviderClient buyingProviderClient = new BuyingProviderClient();
    private List<Vendor> items = null;
    private Vendor selected;

    public VendorController() {
    }

    public Vendor getSelected() {
        return selected;
    }

    public void setSelected(Vendor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

//    private VendorFacade getFacade() {
//        return buyingProviderClient;
//    }

    public Vendor prepareCreate() {
        selected = new Vendor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VendorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VendorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VendorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Vendor> getItems() {
        if (items == null) {
            items = buyingProviderClient.findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    buyingProviderClient.edit(selected);
                } else {
                	buyingProviderClient.remove(selected);
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

    public Vendor getVendor(java.math.BigDecimal id) {
        return buyingProviderClient.find(Integer.valueOf(id.toString()));
    }

    public List<Vendor> getItemsAvailableSelectMany() {
        return buyingProviderClient.findAll();
    }

    public List<Vendor> getItemsAvailableSelectOne() {
        return buyingProviderClient.findAll();
    }

    @FacesConverter(forClass = Vendor.class)
    public static class VendorControllerConverter implements Converter {
    	
    	
    	
//        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VendorController controller = (VendorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vendorController");
            return controller.getVendor(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

//        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Vendor) {
                Vendor o = (Vendor) object;
                return getStringKey(new BigDecimal(o.getVendorId()));
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vendor.class.getName()});
                return null;
            }
        }

    }

}
