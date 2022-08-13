import axios from 'axios'
import drf from '@/api/drf'
import router from '@/router'

export default {
  
  state: {
    counselors: [],
    counselor:[],
    searched_counselor: [],
    filtering_counselor: [],
    favorite: '',
    careers: [],
    categories:[],
    certificates:[],
    reviews:[],
  },

  getters: {
    counselors: state => state.counselors,
    counselor: state=> state.counselor,
    searched_counselor: state => state.searched_counselor,
    filtering_counselor: state => state.filtering_counselor,
    favorite: state => state.favorite,
    
  },

  mutations: {

    SET_COUNSELORS:(state, counselors)=> state.counselors=counselors,
    SET_COUNSELOR:(state,counselor)=>state.counselor=counselor,
    SET_COUNSELOR_REVIEWS: (state, reviews) => (state.counselor.reviews = reviews),
    SEARCH_COUNSELOR: (state, searched_counselor) => state.searched_counselor = searched_counselor,
    FILTER_COUNSELOR: (state, filtering_counselor) => state.filtering_counselor = filtering_counselor,
    SET_FAVORTIE: (state, favorite) => state.favorite = favorite,
  },

  actions: {
    
    fetchCounselors({ commit, getters }) {
      let s='';
      let q=0;
      let w = 1000000;
      let a = false;
      axios({
        
       url: drf.counselors.counselors()+ "category" +s +"&downPrice=" +q + "&upPrice=" + w + "&reviewCntUp=" + a + "&reviewCntDown=" + a + "&reviewScoreUp=" +a + "&reviewScoreDown=" +a + "&favorite=" + a,
       method : 'GET',
       headers: {
        'Content-Type': 'application/json',
        'Authorization': getters.authHeader2
        }
      })
      .then(res => {
        commit('SET_COUNSELORS', res.data)
      })       
        
      .catch(err => {
        console.error(err)
      })
    },


    fetchCounselor({ commit, getters }, counselorId) {
      axios({
       url: drf.counselors.counselor(counselorId),
       method : 'GET',
       headers: {
        'Content-Type': 'application/json',
        'Authorization': getters.authHeader2
        }
      })
      .then(res =>{
        commit('SET_COUNSELOR', res.data)})       
      .catch(err => {
        console.error(err.response)
        if (err.response.status === 404) {
          router.push({ name: 'NotFound404' })
        }
      })
    },

    








    fetchCounselorList({ commit, getters }) {
      axios({
       url: drf.counselors.counselorList(),
       method : 'GET',
       headers: getters.authHeader,
      })
      .then(res => commit('SET_COUNSLORS', res.data))       
        
      .catch(err => console.error(err.response))
    },



    
    searchCounselors({ commit, getters }, keywords ) { 
      
      axios({
        url: drf.counselors.search(keywords),
        method : 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': getters.authHeader2
          }
       })
       .then(res => {
          commit('SEARCH_COUNSELORS', res.data)
          router.push({ name: 'counselorSearch'}).catch(()=>{})
        
        })
      
       .catch(err => {
         console.error(err.response)
         if (err.response.status === 404) {
           router.push({ name: 'NotFound404' })
         }
       })
      
    },

    filterCounselors({ commit, getters }, categoryId ) { 
      
      axios({
        url: drf.counselors.filter(categoryId),
        method : 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': getters.authHeader2
          }
       })
       .then(res => {
          commit('FILTER_COUNSELORS', res.data)
          router.push({ name: 'counselorList'}).catch(()=>{})
        
        })
      
       .catch(err => {
         console.error(err.response)
         if (err.response.status === 404) {
           router.push({ name: 'NotFound404' })
         }
       })
      
    },
    



    favoriteCounselor({ state, commit, getters }, memberId) {

      axios({
        url: drf.counselors.favoriteCounselor(memberId),
        method: 'post',
        headers: getters.authHeader,
      })
        .then(res => {commit('SET_COUNSELOR', res.data)
            let favorite = false
            state.counselor.favorite_users.forEach((el) => {
            if (el.username === getters.currentUser.username){
              favorite = true
            }
          })
          commit('SET_FAVORITE', favorite)
        })
        .catch(err => console.error(err.response))
    },

    isfavorite({ state, commit, getters}) {
      let favorite = false
      state.counselor.favorite_users.forEach((el) => {
      if (el.username === getters.currentUser.username){
        favorite = true
      }
      })
      commit('SET_FAVORITE', favorite)

    },

		
    




  },
}