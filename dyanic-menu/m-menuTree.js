import { queryMenu } from '@/services/menuTree';

export default {
    namespace: 'menuTree',

    state: {
        menuData: [],
    },

    effects: {
        *getMenu(_, { call, put }) {
            const response = yield call(queryMenu);
            yield put({
                type: 'menuResult',
                payload: response,
            });
        },
    },

    reducers: {
        menuResult(state, action) {
            return {
                ...state,
                menuData: action.payload,
            };
        },
    },
};