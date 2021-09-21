<template>
  <div>
    <button @click="click">Click {{ count }} times</button>
    <i class="fab fa-vuejs"></i>
  </div>
</template>

<script>
export default {
  name: "AboutComponent",
  data() {
    return {
      count: 0,
    }
  },
  created(){
    alert('criei o componente');
  },
  methods: {
    click() {
      this.count++;
    },
  },
};
</script>
