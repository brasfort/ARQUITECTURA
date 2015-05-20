package edu.uniminuto.utils;

public class SongStockUtils {
    public static String getParameter(
            javax.servlet.http.HttpServletRequest request, 
            String parametername) {
        
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }
}
