import axios from 'axios';
import authHeader from './auth-header';

const KUPOVINA_API_BASE_URL = "http://localhost:8080/api/kupovine";

class KupovineService { 

    getKupovine(config){
        return axios.get(KUPOVINA_API_BASE_URL, config , { headers: authHeader() });
    }

    getKupovinaById(kupovinaId){
        return axios.get(KUPOVINA_API_BASE_URL + '/' + kupovinaId , { headers: authHeader() });
    }

    updateKupovina(kupovina, kupovinaId){
        return axios.put(KUPOVINA_API_BASE_URL + '/' + kupovinaId, kupovina , { headers: authHeader() });
    }

    deleteKupovina(kupovinaId){
        return axios.delete(KUPOVINA_API_BASE_URL + '/' + kupovinaId , { headers: authHeader() });
    }

    createKupovina(username){
        return axios.post(KUPOVINA_API_BASE_URL + '/kreirajkupovinu/', username,  { headers: authHeader() });
    }
    
    kupi(kupovinaId){
        return axios.post(KUPOVINA_API_BASE_URL + '/' + kupovinaId + '/kupi' ,  { headers: authHeader() } );
    }


}

export default new KupovineService()