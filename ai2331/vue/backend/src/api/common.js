import request from '@/utils/request'

export function lookup(groupCode) {
  return request({
    url: `/util/lkp/options/${groupCode}`,
    method: 'get'
  })
}

export function lookupNames(groupCode, codes) {
  var _codes = []
  if (codes) {
    _codes = codes.split(',')
  }
  return request({
    url: `/util/lkp/options/name/multi/${groupCode}`,
    method: 'get',
    params: {
      codes: _codes
    }
  })
}

export function choices(choiceCode, q, cp, ps) {
  return request({
    url: `/util/chioce/grid/${choiceCode}`,
    method: 'get',
    params: {
      q,
      cp,
      ps
    }
  })
}
