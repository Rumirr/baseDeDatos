package AEMET.model;

/**
 *
 * @author horabaixa
 */
public class IntegracionDatos {

//    //Estacion es = Estacion de entidad.
//    //Aemet.api.estacion
//    //Se crea un arrayList con la entidad.
//    public static ArrayList<Estacion> estaciones() throws Exception {
//
//        Estacion e = new Estacion();
//
//        //Se crea un arrayList cogiendo desde la API.
//        ArrayList<AEMET.API.Estacion> estacionesAPI = APIUtils.getStations();
//
//        //Se crea mi propio arrayList para mis entidad.
//        ArrayList<AEMET.model.entities.Estacion> estacion = new ArrayList<>();
//
//        //Se crea un for para cara ir añadiendo mi arrayList con el arrayList de la API.
//        for (AEMET.API.Estacion estaciones : estacionesAPI) {
//
//            e.setId(estaciones.getIndicativo());
//            e.setNombre(estaciones.getNombre());
//
//            // TODO: Provincia añadir ID    
//        }
//
//        return estacion;
//
//    }
//
//    public static ArrayList<EstMeteorologia> obsMeteorologica(String id) throws Exception {
//
//        EstMeteorologia estM = new EstMeteorologia();
//
//        ArrayList<AEMET.API.ObservacionEstacion> ObEstaciones = APIUtils.getObservacionEstacion(id);
//        ArrayList<AEMET.model.entities.EstMeteorologia> EstMeteo = new ArrayList<>();
//
//        for (AEMET.API.ObservacionEstacion observacionesEstaciones : ObEstaciones) {
//
//            estM.setEstMeteorologiaPK(new EstMeteorologiaPK(observacionesEstaciones.getIdema(), fecha(observacionesEstaciones.getFint())));
//            estM.setUbicacion(observacionesEstaciones.getUbi());
//            estM.setLongitud(new BigDecimal(observacionesEstaciones.getLon()));
//            estM.setLatitud(new BigDecimal(observacionesEstaciones.getLat()));
//            estM.setAltitud(new BigDecimal(observacionesEstaciones.getAlt()));
//            estM.setPrecAcumulada(new BigDecimal(observacionesEstaciones.getPrec()));
//            estM.setTempMinAire(new BigDecimal(observacionesEstaciones.getTamin()));
//            estM.setTempMaxAire(new BigDecimal(observacionesEstaciones.getTamax()));
//            estM.setPresion(new BigDecimal(observacionesEstaciones.getPres()));
//            estM.setHumedad(new BigDecimal(observacionesEstaciones.getHr()));
//            estM.setVeloMaxViento(new BigDecimal(observacionesEstaciones.getVmax()));
//            estM.setVeloMediaViento(new BigDecimal(observacionesEstaciones.getVv()));
//            estM.setDireccViento(new BigDecimal(observacionesEstaciones.getDv()));
//            estM.setTempInstantanea(new BigDecimal(observacionesEstaciones.getTa()));
//            estM.setDuracionInsolacion(new BigDecimal(observacionesEstaciones.getInso()));
//
//        }
//
//        return EstMeteo;
//
//    }
////    private Object instant;
////    
////    private static Date fecha(String fechaHora){
////        if (fechaHora == null) {
////            
////            return Date.from(Instant.now());
////            
////        }else{
////            
////           fechaHora = fechaHora + "Z";
////           
////           return fecha(Date.from(LocalDateTime.fecha));
////        }
////    }
//
//    public static Date fecha(String fecha) throws ParseException {
//        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss ZZZ");
//        Date fechaDate = null;
//        if (fecha == null) {
//            
//            return Date.from(Instant.now());
//            
//        }else{
//            fechaDate = formato.parse(fecha);
//            return fechaDate;
//        }
//        
//    }
}
