import { lookup, lookupNames } from '@/api/common'

const actions = {
  lookup({ commit }, groupCode) {
    console.info(groupCode)
    console.info('aaaaaaaaaaaaaaa')
    return new Promise((resolve, reject) => {
      lookup(groupCode).then(response => {
        resolve(response)
      }).catch(error => reject(error))
    })
  },
  lookupNames(groupCode, codes) {
    return new Promise((resolve, reject) => {
      lookupNames(groupCode, codes).then(response => {
        resolve(response)
      }).catch(error => reject(error))
    })
  }
}

export default {
  namespaced: true,
  actions
}
