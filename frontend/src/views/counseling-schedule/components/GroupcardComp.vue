<template>
  <div class="page-content-wrapper">
  <div class="blog-wrapper direction-rtl">
    <div class="container">
      <div class="row g-3">
        <div v-for="(re,idx) in request"
            :key="idx"
            :re="re">
          <div v-if="re.counselorId== currentUser.data.userId && re.status==`ACCEPTED` && re.groupId != null"  class="card mb-3" id="today-group-card">
          <div class="card position-relative shadow-sm">
            <div class="card-body">
              <div class="card-body d-flex align-items-center">
                  <div>
                    <span class="badge bg-danger rounded-pill mb-2 d-inline-block"> {{ idx+1 }}</span>
                    <h3 class="blog-title d-block text-dark board-title">그룹 이름<br/>{{re.title}}</h3>
                    <router-link :to="{ name: 'counseling', params : {counselingId : re.id}}">
                      <button id="open-room" class="w-btn w-btn-charge">상담방 개설</button>
                    </router-link>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</template>
<script>
import { mapGetters,mapActions } from 'vuex'
export default {
    name : 'GroupcardComp',
     data () {
      return {
       today : this.$route.params.today
    }
    },
    props:{
      request: Array,
      day: Object
    },
     computed:{
    ...mapGetters(['requests','currentUser']),
  },
  methods: {
    ...mapActions(['fetchRequests', 'fetchCurrentUser']),
  },
  created() {
    this.fetchCurrentUser()
    this.fetchRequests()
}

  }

</script>

<style>

.group-today-cards{
  max-width:90%;
  margin:auto;
  text-decoration: none;
}

.personal-request-index{
  background-color: bisque;
   color:bisque;
}
a:link {
  text-decoration: none;
}

</style>