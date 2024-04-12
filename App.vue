<!-- <template>  
    <div class="app">  
        <img class="bg-img" src="/static/bg.jpg" />  
        <router-view />  
    </div>  
</template> --> 
<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	@import "uview-ui/index.scss"; 
</style>
