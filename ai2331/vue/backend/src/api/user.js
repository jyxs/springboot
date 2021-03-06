import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    params: {
      'uname': data.username,
      'pwd': data.password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/me',
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}
