import request from '@/utils/request'

export function authon() {
  return request({
    url: '/authon',
    method: 'post'
  })
}
