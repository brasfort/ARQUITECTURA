/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jhon Gómez
 */
public class Functions {
    private static final Calendar calendar = Calendar.getInstance();
    public static final String[] dias = {"domingo", "lunes", "martes", 
        "miércoles", "jueves", "viernes", "sábado", "domingo"};
    
    public static final String[] meses = {"enero", "febrero", "marzo", 
        "abril", "mayo", "junio", "julio", "agosto", "septiembre", 
        "octubre", "noviembre", "diciembre"};
    
    public static final String[] extensionesPermitidas = {
        "jpg", "jpeg", "gif", "png"};
    
    public static final String[] MIMEPermitido = {"image/gif", "image/jpeg", 
        "image/pjpeg", "image/png"};
    
//    public static final String
//	$contents = array(
//                new Item("rol", "Roles 	"),
//                new Item("usuario", "Usuarios"),
//                #new Item("valorestilo", "Estilos"),
//                new Item("area", "Areas"),
//                #new Item("materia", "Materias"),
//                #new Item("pregunta", "Preguntas"),
//                new Item("estilo", "Modelos"),
//                new Item("prueba", "Pruebas"),
//                new Item("prRestringido", "Resolver pruebas"),
//                new Item("roles", "Roles de usuario")
//            );


    public static String getMoney(String str, boolean sign, int decimals) {
        String ret = "";
        String pre = (Integer.valueOf(str) < 0) ? "- " : "";
        str = String.valueOf(Math.abs(Integer.valueOf(str)));
        int length = str.length();
        String aux = str;
        if (length < 3) {
            ret += str;
        } else {
            while (length > 3) {
                
                
                
                ret = "." + aux.substring(length - 3, length) + ret;
                aux = aux.substring(0, length - 3);
                length -= 3;
            }
            
            ret = aux.substring(0, length) + ret;
        }

        String dot = (decimals > 0) ? "." : "";
        String dec = "";
        for (int i = 0; i < decimals; i++) {
            dec = dec + "0";
        }
        

        ret = (sign) ? pre + "$ " + ret + dot + dec: pre + ret; 
        return ret;
    }

    public static String getFDate(Date date) {
        
        calendar.setTime(date);
        
        return dias[calendar.get(Calendar.DAY_OF_WEEK)] + ", "
                + calendar.get(Calendar.DATE) + " de "
                + meses[calendar.get(Calendar.MONTH)] + " de "
                + calendar.get(Calendar.YEAR);
    }

    public static String getLDate(Date date) {
        
        calendar.setTime(date);
        
        
        return calendar.get(Calendar.DAY_OF_WEEK) + " de "
                + meses[calendar.get(Calendar.MONTH)] + " de "
                + calendar.get(Calendar.YEAR);	
    }

    public static String getFHDate(Date date) {
        
        calendar.setTime(date);
        
            return dias[calendar.get(Calendar.DAY_OF_WEEK)] + ", "
                + calendar.get(Calendar.DATE) + " de "
                + meses[calendar.get(Calendar.MONTH)] + " de "
                + calendar.get(Calendar.YEAR) + " a las "
                + calendar.get(Calendar.MINUTE) + " de "
                + calendar.get(Calendar.SECOND) + " de "
                + calendar.get(Calendar.AM_PM) + " de ";
        
    }

//    public static final String getXHTML(String str) {
//            String[] pattern = {
//                    "/align=\"+(\w+)+\"/i",
//                    "/<br>/i",
//                    "/<img src=\"([*-} ]+)\">/i",
//                    "/<font size=\"([\w. ]{1,})\">/i",
//                    "/<\/font>/i",
//                    "/\[youtube a=(\d+) h=(\d+)\](.+)\[\/youtube\]/i",
//                    "/\[vimeo a=(\d+) h=(\d+)\](.+)\[\/vimeo\]/i"
//            );
//            String replace = Array(
//                    0 => "style="text-align: $1"",
//                    1 => "<br />",
//                    2 => "<span class="media"><img src="${1}" /></span>",
//                    3 => "<span style="font-size: ${1}">",
//                    4 => "</span>",
//                    5 => "<span class="video"><object style="width: ${1}px; height: ${2}px;" type="application/x-shockwave-flash" data="${3}"><param name="wmode" value="transparent" /></object></span>",
//                    6 => "<span class="video"><object style="width: ${1}px; height: ${2}px;" allowfullscreen="true" data="http://vimeo.com/moogaloop.swf?clip_id=${3}&amp;server=vimeo.com&amp;" type="application/x-shockwave-flash"><param allowscriptaccess="always" allowfullscreen="true" value="http://vimeo.com/moogaloop.swf?clip_id=${3}&amp;server=vimeo.com" name="movie" /></object></span>"
//            );
//
//            return preg_replace($pattern, $replace, $str);
//    }
//
//    function reverseXHTML($str) {
//            $pattern = Array(
//                    0 => "/<span class="media"><img src="([*-} ]+)" \/><\/span>/i",
//                    1 => "/<span class="video"><object style="width: (\d+)px; height: (\d+)px;" type="application\/x-shockwave-flash" data="([*-} ]+)"><param name="wmode" value="transparent" \/><\/object><\/span>/i",
//                    2 => "/<span class="video"><object style="width: (\d+)px; height: (\d+)px;" allowfullscreen="true" data="http:\/\/vimeo\.com\/moogaloop\.swf\?clip_id=(\w+)&amp;server=vimeo\.com&amp;" type="application\/x-shockwave-flash"><param allowscriptaccess="always" allowfullscreen="true" value="http:\/\/vimeo\.com\/moogaloop\.swf\?clip_id=(\w+)&amp;server=vimeo\.com" name="movie" \/><\/object><\/span>/i"
//            );
//            $replace = Array(
//                    0 => "<img src="${1}">",
//                    1 => "[youtube a=$1 h=$2]$3[/youtube]",
//                    2 => "[vimeo a=$1 h=$2]$3[/vimeo]"
//            );
//
//            return preg_replace($pattern, $replace, $str);
//    }
//
//    function uploadFile($id, $last = "") {
//            global $allowedExtensions, $allowedMIME;
//            $file = $_FILES[$id];
//            $filename = $file["name"];
//            $explode = explode(".", $filename);
//            $extension = end($explode);
//
//            if ($file["error"] > 0) {
//                    return $file["error"];
//            }
//
//            $explode = explode("/", $last);
//            if (count($explode) > 1) {
//                    $lastfile = "images/".end($explode);
//                    if (file_exists($lastfile)) {
//                            unlink($lastfile);
//                    }
//            }
//
//            if (in_array($file["type"], $allowedMIME) && in_array(strtolower($extension), $allowedExtensions)) {
//                    if (file_exists("images/$filename")) {
//                    return "El archivo "$filename" ya existe.";
//            } else  {
//                    if (move_uploaded_file($file["tmp_name"], "images/$filename")) {
//                            return "http://localhost/educrud/mcprototipo/images/$filename";
//                    }
//                    return "Error: el archivo no se pudo subir.";
//                }
//            }
//            return "Formato de archivo no permitido.";
//    }
//
//    function since($date) {
//            if ($date === null || $date == "" || $date === "null" || $date === "NULL" || $date == "0000-00-00 00:00:00") {
//                    return "no asignado.";
//            }
//
//            $now = date("Y-n-j H:i:s");
//            $time = (strtotime($now) - strtotime($date));
//            if ($time / 604800 > 1) {
//                    return getLDate($date);
//            }
//            // $time /= 60;
//
//            $vars = Array(
//                    0 => Array("number" => 60, "measure" => "minutos", "limit" => 60),
//                    1 => Array("number" => 3600, "measure" => "horas", "limit" => 24),
//                    2 => Array("number" => 86400, "measure" => "días", "limit" => 7));
//
//
//            foreach ($vars as $var) {
//                    $since = $time / $var["number"];
//                    $str = intval($since);
//                    if (strpos($since, ".") !== false) {
//                            $str++;
//                    }
//                    $limit = $var["limit"];
//                    /*if ($str > $limit && $measure === "días") {
//                    }*/
//
//                    if ($str > 0 && $str < $limit) {
//                            $measure = $var["measure"];
//                            if ($measure === "días") {
//                                    global $days;
//                                    $day = date("N") - $str;
//                                    if ($day <= 0) {  
//                                            $day = $day + 7;
//                                    }
//                                    return "el ".$days[$day];
//                            }
//
//                            if ($str == 1) {
//                                    return "Hace un ".substr($measure, 0, strlen($measure) - 1);
//                            }
//                            return "Hace $str $measure"; 
//                    } //else continue;
//            }
//            return getLDate($date);
//    }
//
//    function sinceonSpan($date, $withHour = true) {
//            if ($withHour === true) {
//                    return "<span title="".getFHDate($date)."">".since($date)."</span>";
//            }
//            return "<span title="".getLDate($date)."">".since($date)."</span>";
//    }
//
//    function getTime($time) {
//            if ($time != "NULL" && $time != "00:00:00")  {
//                    return $time;
//            }
//            return "no asignado";
//    }
//
//    function isValidDate($date, $withHour = false) {
//            if (preg_match("/^[\d]{4}-[\d]{1,2}-[\d]{1,2}$/", $date)) {
//                    list($year, $month, $day) = explode("-", $date);
//                    return checkdate($month, $day, $year);
//            }
//            return "La fecha no tiene un formato correcto: AAAA-MM-DD";
//    }
//
//    function resume($string, $length = 15) {
//            if (strlen($string) > $length + 3) {
//                    return substr($string, 0, $length)."...";
//            }
//            return $string;
//    }
//
//    function getParagraphs($numberofTabs, $title, $string) {
//            $tabs = getTabs($numberofTabs);
//
//            $str = "";
//            if ($title != "") {
//                    $str = $tabs."<p><strong>".$title."</strong></p>".PHP_EOL;
//            }
//            if ($string != "") {
//                    // replace PHP_EOL for br HTML tags
//                    $string = str_replace(PHP_EOL, "<br />", $string);
//                    // quit last br
//                    if (endsWith($string, "<br />")) {
//                            $string = substr($string, 0, strlen($string) - 6); 
//                    }
//                    $str = $str.$tabs."<p>".str_replace("<br />", "</p>".PHP_EOL."<p>", $string)."</p>".PHP_EOL;
//            }
//            return $str;
//    }

    public static final Object isNumber(StringBuffer string, int mayorque, int menorque ) {
            String ret = "";
            
            if (string.toString().matches("^\\d+$")) {
                int inte = Integer.valueOf(ret);
                
                if (mayorque != 0 && mayorque < inte) {
                    ret = inte + " debe ser mayor a " + mayorque;
                }
                if (menorque != 0 && menorque > inte) {
                    ret = inte + " debe ser menor a " + menorque;
                }
                
            } else {
                ret = string + " no es un número" ;
        }
            return (ret.isEmpty()) ? true : ret;
    }

    public static final boolean isMail(String string) {
        return string.matches("\"/^[\\w_-]+@((\\w+).)+.[\\w]+$/i\"");
    }

    public static final boolean isAlphaNumeric(String string) {
        return string.matches("/^[áéíóúñÁÉÍÓÚÑa-zA-Z ]+$/i");
    }

//    public static final int yearsOld(Date fecha) {
//        Calendar choy = calendar.getInstance();
//        
//        calendar.setTime(fecha);
//        
//        return choy.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
//        
//            $today = time();
//            $Ago = mktime(0, 0, 0, date("m"), date("d"), date("Y") - $years);
//            return strtotime($value) < strtotime($Ago);
//    }	
//
//    function getRandomList($size) {
//            $rand;
//            $orderArray = array();
//            for ($i = 0; $i < $size; $i++) {
//                    $rand = rand(0, $size - 1);
//                    while (array_search($rand, $orderArray) !== false) {
//                            $rand = rand(0, $size - 1);
//                    }
//                    $orderArray[$i] = $rand;
//            }
//            return $orderArray;
//    }

    public static final int getAge(Date fecha) {
        
        Calendar choy = Calendar.getInstance();
        
        calendar.setTime(fecha);
        
       
        int edad = choy.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        
        if (choy.get(Calendar.MONTH) < calendar.get(Calendar.MONTH)) {
            edad++;
        } else if (choy.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
            if (choy.get(Calendar.DATE) < calendar.get(Calendar.DATE)) {
                edad++;
            }
        }
        return edad;
    }

    public static String getAgeonSpan(Date date) {
            return "<span title=\"\""+getLDate(date)+"\">"+getAge(date)+"</span>";
    }




    class Item {
        private String valor = "";
        private String texto = "";

        public Item() { }
        public Item(String valor, String texto) {
            this.valor = valor;
            this.texto = texto;
        }

        /**
         * @return the valor
         */
        public String getValor() {
            return valor;
        }

        /**
         * @param valor the valor to set
         */
        public void setValor(String valor) {
            this.valor = valor;
        }

        /**
         * @return the texto
         */
        public String getTexto() {
            return texto;
        }

        /**
         * @param texto the texto to set
         */
        public void setTexto(String texto) {
            this.texto = texto;
        }
    }
}
