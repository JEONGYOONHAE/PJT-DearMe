import axios from 'axios'


export default {
    state: {
        counselings: [],
        counseling: [],
    },
    getters: {
        counselings: state => state.counselings,
        counseling: state => state.counseling,
    },
    mutations: {
        SET_COUNSELINGS: (state, counselings) => state.counselings=counselings,
        SET_COUNSELING: (state, counseling) => state.counseling=counseling,
    },
    actions: {

        fetchCounselings({commit, getters}) {
            axios({
                url: 'https://i7d206.p.ssafy.io:4443/counselings',
                method: 'GET',
                headers: {
                    'Authorization': getters.authHeader2
                }
            })
            .then(res => {
                commit('SET_COUNSELINGS', res.data)
            })
            .catch(err => {
                console.error(err)
            })
        }
    }
}