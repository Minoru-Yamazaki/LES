<template>
  <div>
    <app-nav></app-nav>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";

export default {
  name: "HomeLogComponent",
  components: {
    "app-nav": NavComponent,
  },
  data() {
    return {
      cliente: null
    };
  },
  created() {
  },
  methods: {
    click() {},
  },
};
</script>
