import { authon } from '@/api/authen'

const getDefaultState = () => {
  return {
    menus: [],
    prems: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_AUTHEN: () => {
    state.menus = []
    state.prems = []
  },
  SET_AUTHEN: (state, menus, prems) => {
    state.menus = menus
    state.prems = prems
  }
}

const actions = {
  userAuthon({ commit }) {
    return new Promise((resolve, reject) => {
      authon().then(response => {
        const { code, datas } = response
        if (code === '0') {
          return reject('获取用户权限异常')
        }
        if (!datas) {
          return reject('用户没有任何权限')
        }
        commit('SET_AUTHEN', datas['menus'], datas['prems'])
        // console.info(datas['menus'])
        // console.info(datas['prems'])
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
