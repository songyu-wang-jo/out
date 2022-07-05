import {createStore} from "vuex";
import authStore from "./modules/auth.store";

const store = createStore({
    modules: {
        auth: authStore
    }
})

export default store