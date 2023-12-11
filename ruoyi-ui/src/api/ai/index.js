import request from '@/utils/request'

export function askForTable(content) {
  return request({
    url: `/ai/askForTable/${content}`,
    method: 'get',
  })
}
