import axios from 'axios';
import authHeader from './auth-header';

const STAVKE_API_BASE_URL = "http://localhost:8080/api/kupovine/{kupovinaId}/stavke";
const STAVKE_API_BASE_URLS = "http://localhost:8080/api/kupovine/";

class StavkeService { 

	getAllsByKupovinaId(kupovinaId){
        return axios.get(STAVKE_API_BASE_URLS + kupovinaId + '/stavke' ,  { headers: authHeader() } );
    }

    createStavka(stavka){
        return axios.post(STAVKE_API_BASE_URL, stavka ,  { headers: authHeader() } );
    }

    getStavkaById(stavkaId){
        return axios.get(STAVKE_API_BASE_URL + '/' + stavkaId ,  { headers: authHeader() } );
    }


    deleteStavka(stavkaId){
        return axios.delete(STAVKE_API_BASE_URL + '/' + stavkaId ,  { headers: authHeader() } );
    }
    
    kupiStavku(stavkaId,kolicinastavke){
        return axios.post(STAVKE_API_BASE_URL + '/' + stavkaId + '/' + kolicinastavke + '/kupiStavku' ,  { headers: authHeader() } );
    }
    
    resetujStavku(stavkaId){
        return axios.post(STAVKE_API_BASE_URL + '/' + stavkaId  + '/resetujStavku' ,  { headers: authHeader() } );
    }


}

export default new StavkeService()