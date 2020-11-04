import { lookup, lookupNames, choices } from '@/api/common'

const actions = {
  lookup({ commit }, groupCode) {
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
  },
  /**
   * 获取choice列表
   *
   * @param {*} param0
   * @param {*} choiceCode
   * @param {*} q 查询条件
   * @param {*} cp 当前页数
   * @param {*} ps 页面显示数量
   */
  choice({ commit }, choiceCode, q, cp, ps) {
    if (!cp || cp <= 0) {
      cp = 1
    }
    if (!ps) {
      ps = 10
    }
    if (!q) {
      q = ''
    }
    return new Promise((resolve, reject) => {
      choices(choiceCode, q, cp, ps).then(response => {
        resolve(response)
      }).catch(error => reject(error))
    })
  }
}

export default {
  namespaced: true,
  actions
}
