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
  choice({ commit }, params) {
    if (!params.currentPage || params.currentPage <= 0) {
      params.currentPage = 1
    }
    if (!params.pageSize || params.pageSize <= 0) {
      params.pageSize = 10
    }
    if (!params.qkey) {
      params.qkey = ''
    }
    return new Promise((resolve, reject) => {
      choices(params.choiceCode, params.qkey, params.currentPage, params.pageSize).then(response => {
        resolve(response)
      }).catch(error => reject(error))
    })
  }
}

export default {
  namespaced: true,
  actions
}
