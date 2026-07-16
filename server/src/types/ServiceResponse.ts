import http from 'http';

interface ServiceResponsePayload {
	message: string,
	data?: unknown
}

interface IServiceResponse {
	success: boolean,
	statusCode: number,
	payload: ServiceResponsePayload
}

class ServiceResponse implements IServiceResponse {
	success: boolean = false;
	statusCode: number = -1;
	payload: ServiceResponsePayload = { message: "Default service response." }

	get() {
		const response: IServiceResponse = {
			success: this.success,
			statusCode: this.statusCode,
			payload: {
				message: `${this.payload.message} (${http.STATUS_CODES[this.statusCode]})`,
				data: this.payload.data
			}
		};
		return response; 
	}
}

export default ServiceResponse;