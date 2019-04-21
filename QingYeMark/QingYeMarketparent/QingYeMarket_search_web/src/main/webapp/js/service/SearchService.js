app.service('searchService',function ($http) {
    this.search =function (searchMap) {
        return $http.post("ItemSearch/search.do",searchMap)
    }
})